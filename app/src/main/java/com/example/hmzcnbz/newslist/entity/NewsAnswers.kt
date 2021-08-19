package com.example.hmzcnbz.newslist.entity

import com.google.gson.annotations.SerializedName

data class NewsAnswers(
    @SerializedName("sources")
    val newsAnswersSources: List<NewsAnswersSource>,
    @SerializedName("status")
    val status: String
)