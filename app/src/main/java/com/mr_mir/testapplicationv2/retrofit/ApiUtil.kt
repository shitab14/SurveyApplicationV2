package com.mr_mir.testapplicationv2.retrofit

/**
 * Created by Shitab Mir on 28,July,2020
 */
class ApiUtil {
    companion object {
        fun getApiService(): ApiEndpoints{
            val BASE_URL: String = "https://example-response.herokuapp.com"
            return RetrofitBuilder.getClient(BASE_URL)!!.create(ApiEndpoints::class.java)
        }
    }
}