package com.example.hmzcnbz.newslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hmzcnbz.newslist.databinding.RowItemBinding
import com.example.hmzcnbz.newslist.entity.NewsAnswersSource


class NewsAdapter(val newsList: List<NewsAnswersSource>, private val listener: OnItemClickListen) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(position, newsList[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.newsObject = news
    }

    override fun getItemCount() = newsList.size
}

interface OnItemClickListen {
    fun onItemClicked(position: Int, id: String)
}