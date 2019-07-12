package com.sun.okhttp_retrofit.di

import android.content.Context
import com.sun.okhttp_retrofit.data.datasource.ApiService
import com.sun.okhttp_retrofit.utils.GzipRequestInterceptor
import com.sun.okhttp_retrofit.utils.LoggingInterceptor
import com.sun.okhttp_retrofit.utils.NetworkUtils
import com.sun.okhttp_retrofit.utils.ResponseInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


val networkModule = module {
    single { createApiService(androidContext()) }
}

private const val BASE_URL = "https://spring-boot-wall-tags.herokuapp.com/adsharingspace/"

const val CONTENT_TYPE = "Content-Type"

const val VALUE = "application/json"

private fun createApiService(context: Context): ApiService {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BASIC

    val cacheSize = 10 * 1024 * 1024

    val client = OkHttpClient.Builder()
        .addInterceptor {
            val original: Request = it.request()
            val request = original.newBuilder()
                .addHeader(CONTENT_TYPE, VALUE)
                .method(original.method(), original.body())
                .build()
            return@addInterceptor it.proceed(request)
        }
        .addInterceptor(logging)
        .addInterceptor(ForceCacheInterceptor())
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(ResponseInterceptor())
//        .addInterceptor(GzipRequestInterceptor())
        .cache(Cache(context.cacheDir, cacheSize.toLong()))

    getX509TrustManager()?.let {
        SSLSocketFactory.getDefault()?.let { sf ->
            client.sslSocketFactory(sf as SSLSocketFactory, it)
        }
    }

    val retrofit = Retrofit.Builder()
        .client(client.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}

private fun getX509TrustManager(): X509TrustManager? {
    try {
        val trustManagerFactory = TrustManagerFactory
            .getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size == 1 && trustManagers[0] is X509TrustManager) {
            return trustManagers[0] as X509TrustManager
        } else {
            return null
        }
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        return null
    } catch (e: KeyStoreException) {
        e.printStackTrace()
        return null
    }
}


class ForceCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (!NetworkUtils.internetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}

