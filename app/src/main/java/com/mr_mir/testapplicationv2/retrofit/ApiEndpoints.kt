package com.mr_mir.testapplicationv2.retrofit

import com.mr_mir.testapplicationv2.model.SurveyBody
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Shitab Mir on 28,July,2020
 */
interface ApiEndpoints {
    @GET("/getSurvey")
    fun getData(): Call<List<SurveyBody>?>?

}