package com.example.hmzcnbz.newslist.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("sourceImage")
    fun loadImage(view: ImageView, profileImage: String) {
        Glide.with(view.context)
            .load(profileImage)
            .into(view)

    }
}