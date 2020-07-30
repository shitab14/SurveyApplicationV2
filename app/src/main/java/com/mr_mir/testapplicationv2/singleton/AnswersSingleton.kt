package com.mr_mir.testapplicationv2.singleton

/**
 * Created by Shitab Mir on 30,July,2020
 */
class AnswersSingleton {

    companion object {

        var textAnswer: String = ""
        var numberAnswer: String = ""
        var checkboxAnswers: ArrayList<String> = arrayListOf()
        var dropDownAnswer: String = ""
        var multipleChoiceAnswer: String = ""

    }

}