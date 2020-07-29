package com.mr_mir.testapplicationv2.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shitab Mir on 28,July,2020
 */
data class SurveyBody (

    @SerializedName("question")
    val question: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("options")
    val options: String,
    @SerializedName("required")
    val required: Boolean

/*
[
{
question: "How frequent you use our product ?",
type: "dropdown",
options: "Very often, often, Moderate, Not much, Never used",
required: true
},
{
question: "Your contact number ?",
type: "number",
options: "",
required: false
},
{
question: "How are you ?",
type: "multiple choice",
options: "Very Good, Good, Neutral, Bad, Very Bad",
required: true
},
{
question: "Where do you live ?",
type: "text",
options: "",
required: true
},
{
question: "Do you like our product ?",
type: "Checkbox",
options: "Yes, No",
required: true
}
]
* */
)