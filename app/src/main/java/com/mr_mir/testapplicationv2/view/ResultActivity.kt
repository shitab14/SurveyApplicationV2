package com.mr_mir.testapplicationv2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.mr_mir.testapplicationv2.R
import com.mr_mir.testapplicationv2.singleton.AnswersSingleton
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.system.exitProcess

class ResultActivity : AppCompatActivity() {
    private var checkBoxAnswers: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvAnswerText.text = AnswersSingleton.textAnswer
        tvAnswerNumber.text = AnswersSingleton.numberAnswer
        tvAnswerDropDown.text = AnswersSingleton.dropDownAnswer
        tvAnswerMultipleChoice.text = AnswersSingleton.multipleChoiceAnswer
        for (x in 0 until AnswersSingleton.checkboxAnswers.size) {
            checkBoxAnswers += if (x == AnswersSingleton.checkboxAnswers.size-1) {
                AnswersSingleton.checkboxAnswers[x]
            } else {
                " ${AnswersSingleton.checkboxAnswers[x]}, "
            }
        }
        tvAnswerCheckBox.text = checkBoxAnswers

        tvDone.setOnClickListener {
            finish()
//            System.exit(0)
        }

    }
}