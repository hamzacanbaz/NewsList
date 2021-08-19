package com.example.hmzcnbz.newslist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hmzcnbz.newslist.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewsList)

        setContentView(R.layout.activity_main)

    }
}