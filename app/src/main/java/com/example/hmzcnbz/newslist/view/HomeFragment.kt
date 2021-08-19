package com.example.hmzcnbz.newslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hmzcnbz.newslist.R
import com.example.hmzcnbz.newslist.adapters.NewsAdapter
import com.example.hmzcnbz.newslist.adapters.OnItemClickListen
import com.example.hmzcnbz.newslist.databinding.FragmentHomeBinding
import com.example.hmzcnbz.newslist.viewmodel.HomeFragmentViewModel


class HomeFragment : Fragment(), OnItemClickListen {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var viewmodel: HomeFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
        viewmodel = homeFragmentViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.homeFragment = this
        viewmodel.newsList.observe(viewLifecycleOwner, {

            adapter = NewsAdapter(it, this)
            binding.adapter = adapter

        })

        return binding.root
    }

    override fun onItemClicked(position: Int, id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewsFragment(id)
        findNavController().navigate(action)
//        Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
    }


}