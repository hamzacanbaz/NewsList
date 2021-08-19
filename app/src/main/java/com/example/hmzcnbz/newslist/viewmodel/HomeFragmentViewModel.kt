package com.example.hmzcnbz.newslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hmzcnbz.newslist.db.ArticleDb
import com.example.hmzcnbz.newslist.entity.NewsAnswersSource
import com.example.hmzcnbz.newslist.repository.NewsRepository

//class HomeFragmentViewModel(app:Application) : AndroidViewModel(app) {
class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val newsList: LiveData<List<NewsAnswersSource>> get() = _newsList
    private var _newsList = MutableLiveData<List<NewsAnswersSource>>()
    private val newsRepository: NewsRepository

    init {
        val db = ArticleDb.invoke(application).getArticleDao()
        newsRepository = NewsRepository(db)
        loadNews()
        _newsList = newsRepository.getNews()
    }


    fun loadNews() {
        newsRepository.showNews()
    }

}