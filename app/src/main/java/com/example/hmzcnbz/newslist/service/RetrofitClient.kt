package com.example.hmzcnbz.newslist.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object ClassName is a singleton it'll only instance it once and reuse for next call
object RetrofitClient {
    private const val BASE_URL = "https://newsapi.org"
    const val API_KEY = "79a665a5013543f2a9e6f37a863bf653"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService: NewsService by lazy {
        retrofit().create(NewsService::class.java)
    }
    //  by lazy using this keyword, my retrofitService will only be initialised
    //  the first time you call and then you'll reuse that same value

}