package com.example.hmzcnbz.newslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hmzcnbz.newslist.db.ArticleDb
import com.example.hmzcnbz.newslist.entity.Article
import com.example.hmzcnbz.newslist.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val newsAnswersSourceList: LiveData<List<Article>> get() = _sourceList
    private var _sourceList = MutableLiveData<List<Article>>()
    private val newsRepository: NewsRepository

    init {
        val db = ArticleDb.invoke(application).getArticleDao()
        newsRepository = NewsRepository(db)

    }

    fun loadNews(soruceId: String) {
        newsRepository.showSourceNews(soruceId)
        _sourceList = newsRepository.getNewsBySource()
    }

     fun saveFavsNews(article: Article) = viewModelScope.launch {
         newsRepository.addToDb(article)
         println("Added to db1")
     }

    fun getFavsNews() = newsRepository.getFavNews()

     fun deleteNews(article: Article) = viewModelScope.launch {
         newsRepository.deleteFavNews(article)
     }

}