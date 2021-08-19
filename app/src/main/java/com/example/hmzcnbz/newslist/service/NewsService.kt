package com.example.hmzcnbz.newslist.service

import com.example.hmzcnbz.newslist.entity.Details
import com.example.hmzcnbz.newslist.entity.NewsAnswers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/top-headlines/sources?apiKey=79a665a5013543f2a9e6f37a863bf653")
    fun getNews(): Call<NewsAnswers>

    @GET("/v2/top-headlines")
    fun getNewsBySource(
        @Query("sources") source: String,
        @Query("apiKey") api: String
    ): Call<Details>
//    @GET("/v2/everything/?apiKey=1db6bc6105f84eaf9f9d92949260810e")
//    fun searchForNews(@Query("q") searchQuery:String) : Call<NewsDetailsAnswers>

}