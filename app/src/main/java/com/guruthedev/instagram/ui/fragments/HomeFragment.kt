package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.guruthedev.instagram.data.InstaStatus
import com.guruthedev.instagram.databinding.FragmentHomeBinding
import com.guruthedev.instagram.ui.adapter.FeedPostAdapter
import com.guruthedev.instagram.ui.adapter.StoriesAdapter
import com.guruthedev.instagram.viewModel.HomeFragmentViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private var feedPostAdapter: FeedPostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.storyRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.storyRecyclerView.setHasFixedSize(true)
        val statusAdapter = StoriesAdapter(getStatus())
        binding.storyRecyclerView.adapter = statusAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        initView()
        initObserver()
        newInstance()
    }

    private fun initView() {
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        feedPostAdapter = FeedPostAdapter()
        binding.recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(decoration)
            adapter = feedPostAdapter
        }
    }

    private fun initObserver() {
        viewModel.getPostResponse().observe(viewLifecycleOwner) { post ->
            post?.let { instaPost ->
                feedPostAdapter?.setUpdatedData(instaPost.items)
            } ?: run {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getPosts()
    }

    private fun getStatus(): ArrayList<InstaStatus> {
        val statusList = ArrayList<InstaStatus>()
        val statusJSON: String =
            requireContext().assets.open("status.json").bufferedReader().use { it.readText() }
        val status = Gson().fromJson(statusJSON, Array<InstaStatus>::class.java)
        for (statusBar in status)
            statusList.add(InstaStatus(statusBar.id, statusBar.name, statusBar.picture))

        return statusList
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
