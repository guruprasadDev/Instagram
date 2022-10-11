package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.guruthedev.instagram.ui.adapter.PostAdapter
import com.guruthedev.instagram.ui.adapter.StoriesAdapter
import com.guruthedev.instagram.dataClass.InstaStatus
import com.guruthedev.instagram.dataClass.Post
import com.guruthedev.instagram.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.storyRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.storyRecyclerView.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewHome.setHasFixedSize(true)

        val statusAdapter = StoriesAdapter(getStatus())
        binding.storyRecyclerView.adapter = statusAdapter

        val postAdapter = PostAdapter(getPostList())
        binding.recyclerViewHome.adapter = postAdapter
        return binding.root
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

    private fun getPostList(): ArrayList<Post> {
        val postList = ArrayList<Post>()
        val postJSON: String =
            requireContext().assets.open("post.json").bufferedReader().use { it.readText() }
        val post = Gson().fromJson(postJSON, Array<Post>::class.java)
        for (posts in post)
            postList.add(
                Post(
                    posts.id,
                    posts.name,
                    posts.logo,
                    posts.photo,
                    posts.likes,
                    posts.description
                )
            )
        return postList
    }
}