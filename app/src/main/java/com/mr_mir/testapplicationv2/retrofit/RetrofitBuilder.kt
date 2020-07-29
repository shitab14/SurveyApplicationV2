package com.mr_mir.testapplicationv2.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Shitab Mir on 28,July,2020
 */
object RetrofitBuilder {

    private var retrofit: Retrofit? = null
    fun getClient(baseUrl: String?): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        /*.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("User-Agent", System.getProperty("http.agent"))
                .addHeader("portal-name", "manager-app")
                .addHeader("version-code", BuildConfig.VERSION_CODE.toString())
                .build()
            chain.proceed(request)
        }*/

/*
        if (BuildConfig.DEBUG) {
            try {
                builder.addInterceptor(interceptor)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
*/
        val okHttpClient1 = builder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl!!)
            .client(okHttpClient1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
