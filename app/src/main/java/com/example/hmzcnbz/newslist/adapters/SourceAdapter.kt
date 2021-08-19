package com.example.hmzcnbz.newslist.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hmzcnbz.newslist.databinding.DetailRowItemBinding
import com.example.hmzcnbz.newslist.entity.Article
import com.example.hmzcnbz.newslist.viewmodel.NewsFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class SourceAdapter(
    val newsAnswersSourceList: List<Article>,
    private val listener: OnItemClickListen,
    private val adapterType: Int = 0
) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {
    val viewModel: NewsFragmentViewModel = NewsFragmentViewModel(Application())
    //lateinit var newsRepository: NewsRepository

    inner class ViewHolder(val binding: DetailRowItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
            //newsRepository = NewsRepository(db:)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(position, newsAnswersSourceList[position].url)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DetailRowItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = newsAnswersSourceList[position]
        holder.binding.sourceObject = source

        if (adapterType == 1) {
            holder.binding.favButton.visibility = View.INVISIBLE
        } else {
            holder.binding.favButton.setOnCheckedChangeListener { _, isChecked ->

                if (isChecked) {
                    viewModel.saveFavsNews(source)
//                    Snackbar.make(View(Application()), "Article Added to Favorites", Snackbar.LENGTH_LONG).apply {
//                        setAction("Undo") {
//                            viewModel.deleteNews(source)
//                        }
//                        show()
//                    }
                }
            }
        }

//        holder.binding.favButton.setOnClickListener {
//            if(holder.binding.favButton.isChecked){
//                viewModel.saveFavsNews(source)
//            }
//            else{
//                viewModel.deleteNews(source)
//            }
//        }

    }

    override fun getItemCount() = newsAnswersSourceList.size

}
