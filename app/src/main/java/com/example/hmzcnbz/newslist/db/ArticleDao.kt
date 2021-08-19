package com.example.hmzcnbz.newslist.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hmzcnbz.newslist.entity.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)

}