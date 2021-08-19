package com.example.hmzcnbz.newslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hmzcnbz.newslist.R
import com.example.hmzcnbz.newslist.adapters.OnItemClickListen
import com.example.hmzcnbz.newslist.adapters.SourceAdapter
import com.example.hmzcnbz.newslist.databinding.FragmentFavoritesBinding
import com.example.hmzcnbz.newslist.viewmodel.FavoritesFragmentViewModel
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment : Fragment(), OnItemClickListen {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: SourceAdapter
    private lateinit var viewmodel: FavoritesFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val favoritesFragmentViewModel: FavoritesFragmentViewModel by viewModels()
        viewmodel = favoritesFragmentViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorites, container, false)
        binding.newsFragment = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = adapter.newsAnswersSourceList[position]
                viewmodel.deleteFavNews(article)

                Snackbar.make(view, "Deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewmodel.addFavs(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.newsRecycler)
        }

        viewmodel.getAllFavs().observe(viewLifecycleOwner, {
            adapter = SourceAdapter(it, this, 1)
            binding.adapter = adapter
        })
    }

    override fun onItemClicked(position: Int, id: String) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToArticleFragment(id)
        findNavController().navigate(action)
    }

}