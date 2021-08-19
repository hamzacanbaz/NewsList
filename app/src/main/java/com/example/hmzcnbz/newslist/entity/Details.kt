package com.example.hmzcnbz.newslist.entity

import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)