package com.sun.okhttp_retrofit.di

import com.sun.okhttp_retrofit.data.datasource.ApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
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
    single { createApiService() }
}

private const val BASE_URL = "https://spring-boot-wall-tags.herokuapp.com/adsharingspace/"

const val CONTENT_TYPE = "Content-Type"

const val VALUE = "application/json"

private fun createApiService(): ApiService {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BASIC


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
//        LOG.error("Error while retrieving X509 trust manager!", e)
        return null
    } catch (e: KeyStoreException) {
//        LOG.error("Error while retrieving X509 trust manager!", e)
        return null
    }

}
