package com.sun.okhttp_retrofit.di

import android.app.Application
import android.content.Context
import com.sun.okhttp_retrofit.R
import com.sun.okhttp_retrofit.data.datasource.ApiService
import com.sun.okhttp_retrofit.ui.MainApplication
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


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

    val retrofit = Retrofit.Builder()
        .client(client.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}

private fun getSSLConfig(context: Context): SSLContext {
    var sslContext: SSLContext? = null
    try {
        val cf = CertificateFactory.getInstance("X.509")
        var ca: Certificate? = null
        context.resources.openRawResource(R.raw.abc).use { cert -> ca = cf.generateCertificate(cert) }

        val keyStoreType = KeyStore.getDefaultType()
        val keyStore: KeyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.trustManagers, null)
    }catch (e:CertificateException){
        e.printStackTrace()
    }

    return sslContext!!
}
