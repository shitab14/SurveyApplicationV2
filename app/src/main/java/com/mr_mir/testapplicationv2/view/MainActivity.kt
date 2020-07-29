package com.mr_mir.testapplicationv2.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mr_mir.testapplicationv2.*
import com.mr_mir.testapplicationv2.adapter.CheckboxAdapter
import com.mr_mir.testapplicationv2.model.SurveyBody
import com.mr_mir.testapplicationv2.mvvmfiles.Resource
import com.mr_mir.testapplicationv2.mvvmfiles.SurveyViewModel
import com.mr_mir.testapplicationv2.retrofit.Util
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private var viewModel: SurveyViewModel? = null
    private var questionSet: List<SurveyBody>? = null
    private var currentQuestionNumber: Int = 0
    private var context: Context = this
    private var multipleChoiceCreated: Boolean = false
    private var dropdownSetup: Boolean = false
    private var checkboxSetup: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loaderView()
        init()
        fetchApiData()
        setView()

//        var option: List<String>? = data?.split(",")

    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(SurveyViewModel::class.java)

        viewModel?.getSurveyLiveData?.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    successView(it.data)
                }
                Resource.Status.ERROR -> {
                    failView(it.message)
                }
                Resource.Status.LOADING -> {
                    loaderView()
                }
            }

        })
    }

    private fun loaderView() {
        tvBack.visibility = GONE
        tvNext.visibility = GONE

        loader.visibility = VISIBLE
        cvQuestion.visibility = GONE
        cvAnswerOptions.visibility = GONE
        noInternet.visibility = GONE
        fail.visibility = GONE
    }

    private fun noInternetView() {
        tvBack.visibility = GONE
        tvNext.visibility = GONE

        loader.visibility = GONE
        cvQuestion.visibility = GONE
        cvAnswerOptions.visibility = GONE
        noInternet.visibility = VISIBLE
        fail.visibility = GONE
    }

    private fun failView(message: String?) {
        tvBack.visibility = GONE
        tvNext.visibility = GONE

        loader.visibility = GONE
        cvQuestion.visibility = GONE
        cvAnswerOptions.visibility = GONE
        noInternet.visibility = GONE
        fail.visibility = VISIBLE
    }

    private fun successView(data: List<SurveyBody>?) {
        tvBack.visibility = GONE
        tvNext.visibility = VISIBLE

        loader.visibility = GONE
        cvQuestion.visibility = VISIBLE
        cvAnswerOptions.visibility = VISIBLE
        noInternet.visibility = GONE
        fail.visibility = GONE

        questionSet = data

        optionView(questionSet)

    }

    @SuppressLint("SetTextI18n")
    private fun optionView(questionSet: List<SurveyBody>?) {

        tvQuestion.text = "(${currentQuestionNumber+1}/${questionSet?.size}) ${questionSet?.get(currentQuestionNumber)?.question}"
        if(questionSet?.get(currentQuestionNumber)?.required!!) {
            tvRequired.visibility = VISIBLE
        } else {
            tvRequired.visibility = GONE
        }
        when (questionSet[currentQuestionNumber].type.toString().toLowerCase(Locale.ENGLISH)) {
            "dropdown" -> {
                if (!dropdownSetup) {
                    setupDropdown(questionSet)
                }
                spinner.visibility = VISIBLE
                etNumber.visibility = GONE
                etText.visibility = GONE
                rvCheckBoxes.visibility = GONE
                rgMultipleChoice.visibility = GONE
            }
            "number"-> {
                spinner.visibility = GONE
                etNumber.visibility = VISIBLE
                etNumber.hint = questionSet[currentQuestionNumber].question.toString()
                etText.visibility = GONE
                rvCheckBoxes.visibility = GONE
                rgMultipleChoice.visibility = GONE
            }
            "multiple choice" -> {
                if(!multipleChoiceCreated) {
                    setupMultipleChoice(questionSet)
                }
                spinner.visibility = GONE
                etNumber.visibility = GONE
                etText.visibility = GONE
                rvCheckBoxes.visibility = GONE
                rgMultipleChoice.visibility = VISIBLE
            }
            "text" -> {
                spinner.visibility = GONE
                etNumber.visibility = GONE
                etText.visibility = VISIBLE
                etText.hint = questionSet[currentQuestionNumber].question.toString()
                rvCheckBoxes.visibility = GONE
                rgMultipleChoice.visibility = GONE
            }
            "checkbox" -> {
                if(!checkboxSetup) {
                    setupCheckbox(questionSet)
                }
                spinner.visibility = GONE
                etNumber.visibility = GONE
                etText.visibility = GONE
                rvCheckBoxes.visibility = VISIBLE
                rgMultipleChoice.visibility = GONE
            }
        }
    }

    private fun setupCheckbox(questionSet: List<SurveyBody>?) {
        val option: List<String>? = questionSet?.get(currentQuestionNumber)?.options?.split(",")

        val checkboxAdapter: CheckboxAdapter =
            CheckboxAdapter(context, option)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvCheckBoxes.layoutManager = layoutManager
        rvCheckBoxes.adapter = checkboxAdapter
        checkboxSetup = true
    }

    private fun setupDropdown(questionSet: List<SurveyBody>?) {
        spinner.visibility = VISIBLE
        spinner.onItemSelectedListener = this

        val option: List<String>? = questionSet?.get(currentQuestionNumber)?.options?.split(",")

        val dataAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, option!!)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter
        dropdownSetup = true
    }

    @SuppressLint("SetTextI18n")
    private fun setupMultipleChoice(questionSet: List<SurveyBody>?) {
        rgMultipleChoice.orientation = LinearLayout.VERTICAL
        val option: List<String>? = questionSet?.get(currentQuestionNumber)?.options?.split(",")
        for (x in 0 until option?.size!!) {
            val rb = RadioButton(context)
            rb.id = View.generateViewId();
            rb.text = option[x]
            rb.setOnClickListener(this);
            rgMultipleChoice.addView(rb);
        }
        multipleChoiceCreated = true
    }

    private fun fetchApiData() {
        if(Util.isNetworkConnected(context)) {
            viewModel?.getSurveyRequest()
        } else {
            noInternetView()
        }
    }

    private fun setView() {
        //Listeners
        tvNext.setOnClickListener(this)
        tvBack.setOnClickListener(this)
        tvSubmit.setOnClickListener(this)
        noInternet.setOnClickListener(this)
        fail.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tvNext -> {
                Util.hideKeyboard(context as Activity)
                currentQuestionNumber++
                tvBack.visibility = VISIBLE
                if (currentQuestionNumber < questionSet?.size!!) {
                    optionView(questionSet)
                }
                if(currentQuestionNumber == questionSet?.size!!-1){
                    tvNext.visibility = GONE
                    tvSubmit.visibility = VISIBLE
                }

            }

            R.id.tvBack -> {
                Util.hideKeyboard(context as Activity)
                currentQuestionNumber--
                tvSubmit.visibility = GONE
                tvNext.visibility = VISIBLE
                if (currentQuestionNumber > -1) {
                    optionView(questionSet)
                }

                if (currentQuestionNumber == 0) {
                    tvBack.visibility = GONE
                }

            }

            R.id.tvSubmit -> {
                Util.showToast(context, "Didn't get any requirements for this function :)")
            }

            R.id.fail -> {
                fetchApiData()
            }

            R.id.noInternet -> {
                fetchApiData()
            }

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
//        todo
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinner.setSelection(position)
    }
}