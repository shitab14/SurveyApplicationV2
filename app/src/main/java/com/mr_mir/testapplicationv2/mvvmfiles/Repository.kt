package com.mr_mir.testapplicationv2.mvvmfiles

import com.mr_mir.testapplicationv2.model.SurveyBody
import com.mr_mir.testapplicationv2.retrofit.ApiUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Shitab Mir on 28,July,2020
 */
object Repository {
    fun getSurvey(callback: SurveyViewModel.LiveDataCallback) {

        callback.getSurveyResponse(Resource.loading())

        ApiUtil.getApiService()
            .getData()?.enqueue(object : Callback<List<SurveyBody>?> {

                override fun onResponse(
                    call: Call<List<SurveyBody>?>,
                    response: Response<List<SurveyBody>?>
                ) {
                    if (response.isSuccessful) {
                        callback.getSurveyResponse(
                            Resource.success(
                                response.body()
                            )
                        )
                    } else{
                        callback.getSurveyResponse(
                            Resource.error(
                                response.errorBody()?.string() ?: "Failed to get data."
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<List<SurveyBody>?>, t: Throwable) {
                    callback.getSurveyResponse(
                        Resource.error(
                            t.localizedMessage ?: "Failed to get data."
                        )
                    )
                }

            })

    }

}