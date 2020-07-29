package com.mr_mir.testapplicationv2.mvvmfiles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mr_mir.testapplicationv2.model.SurveyBody

/**
 * Created by Shitab Mir on 28,July,2020
 */
class SurveyViewModel: ViewModel() {

    val getSurveyLiveData = MutableLiveData<Resource<List<SurveyBody>>>()

    fun getSurveyRequest(){
        Repository.getSurvey(object :
            LiveDataCallback {
            override fun getSurveyResponse(response: Resource<List<SurveyBody>>) {
                getSurveyLiveData.value = response
            }
        })

    }

    interface LiveDataCallback  {
        fun getSurveyResponse (response: Resource<List<SurveyBody>>)
    }

}