package com.example.hmzcnbz.newslist.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hmzcnbz.newslist.db.ArticleDao
import com.example.hmzcnbz.newslist.entity.Article
import com.example.hmzcnbz.newslist.entity.Details
import com.example.hmzcnbz.newslist.entity.NewsAnswers
import com.example.hmzcnbz.newslist.entity.NewsAnswersSource
import com.example.hmzcnbz.newslist.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val dao: ArticleDao) {


    private val newsList: MutableLiveData<List<NewsAnswersSource>> = MutableLiveData()
    private val articlesList: MutableLiveData<List<Article>> = MutableLiveData()
    private val NewsService = RetrofitClient.retrofitService

    fun getNews(): MutableLiveData<List<NewsAnswersSource>> {
        return newsList
    }

    fun getNewsBySource(): MutableLiveData<List<Article>> {
        return articlesList
    }

    fun showNews() {
        NewsService.getNews().enqueue(object : Callback<NewsAnswers> {
            override fun onResponse(call: Call<NewsAnswers>?, response: Response<NewsAnswers>) {
                val list = response.body()!!.newsAnswersSources
                newsList.value = list

            }

            override fun onFailure(call: Call<NewsAnswers>?, t: Throwable?) {
                Log.e("Fail", t?.message.toString())
            }
        })

    }

    fun showSourceNews(sourceId: String) {
        NewsService.getNewsBySource(sourceId, RetrofitClient.API_KEY)
            .enqueue(object : Callback<Details> {
                override fun onResponse(call: Call<Details>?, response: Response<Details>) {
                    val list = response.body()!!.articles
                    articlesList.value = list

                }

                override fun onFailure(call: Call<Details>, t: Throwable) {
                    Log.e("Fail", t.message.toString())
                }


            })
    }

    suspend fun addToDb(article: Article) = dao.insertOrUpdate(article)

    fun getFavNews() = dao.getAllArticles()

    suspend fun deleteFavNews(article: Article) = dao.delete(article)
}