package com.example.hmzcnbz.newslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hmzcnbz.newslist.db.ArticleDb
import com.example.hmzcnbz.newslist.entity.Article
import com.example.hmzcnbz.newslist.repository.NewsRepository
import kotlinx.coroutines.launch

class FavoritesFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val newsRepository: NewsRepository

    init {
        val dao = ArticleDb.invoke(application).getArticleDao()
        newsRepository = NewsRepository(dao)
    }

    fun addFavs(article: Article) = viewModelScope.launch {
        newsRepository.addToDb(article)
    }

    fun getAllFavs() = newsRepository.getFavNews()

    fun deleteFavNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteFavNews(article)
    }

}