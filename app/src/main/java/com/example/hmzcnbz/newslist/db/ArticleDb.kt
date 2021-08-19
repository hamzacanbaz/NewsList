package com.example.hmzcnbz.newslist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hmzcnbz.newslist.entity.Article

@Database(
    entities = [Article::class],
    version = 1
)

// Room Db abstract olmalıdır.
@TypeConverters(Converters::class)
abstract class ArticleDb : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDb(context).also {
                instance = it
            }
        }

        private fun createDb(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDb::class.java,
            "article_db.db"
        ).build()
    }

}