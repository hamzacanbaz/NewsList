package com.example.hmzcnbz.newslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hmzcnbz.newslist.R
import com.example.hmzcnbz.newslist.adapters.OnItemClickListen
import com.example.hmzcnbz.newslist.adapters.SourceAdapter
import com.example.hmzcnbz.newslist.databinding.FragmentNewsBinding
import com.example.hmzcnbz.newslist.viewmodel.NewsFragmentViewModel


class NewsFragment : Fragment(), OnItemClickListen {

    private val args: NewsFragmentArgs by navArgs()

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: SourceAdapter
    private lateinit var viewmodel: NewsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsFragmentViewModel: NewsFragmentViewModel by viewModels()
        viewmodel = newsFragmentViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_news, container, false)
        binding.newsFragment = this

        viewmodel.loadNews(args.sourceId)

        viewmodel.newsAnswersSourceList.observe(viewLifecycleOwner, {

            adapter = SourceAdapter(it, this)
            binding.adapter = adapter

        })
        binding.favNews.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToFavoritesFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onItemClicked(position: Int, id: String) {
        val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(id)
        findNavController().navigate(action)
    }


}