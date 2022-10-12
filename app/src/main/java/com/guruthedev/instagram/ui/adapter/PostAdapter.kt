package com.guruthedev.instagram.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.dataClass.Post
import com.guruthedev.instagram.databinding.PostListItemBinding
import com.guruthedev.instagram.utils.addReadMore
import com.guruthedev.instagram.utils.loadImageUrl
import com.guruthedev.instagram.utils.loadImageUrlLogo


class PostAdapter(private val postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var context: Context? = null

    override fun onCreateViewHolder(instgramPost: ViewGroup, feedPost: Int): PostViewHolder {
        val binding =
            PostListItemBinding.inflate(LayoutInflater.from(context), instgramPost, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: PostViewHolder, feedPost: Int) {
        val post = postList[feedPost]
        val binding = viewHolder.postBinding
        binding.instagramPost = post
        binding.apply {
        context?.let {
                addReadMore(
                    text = post.description,
                    textView = descriptionTxt,
                    context = it
                )
                loadImageUrlLogo(
                    imageURL = post.logo,
                    context = it,
                    width = 800,
                    height = 400,
                    imageView = imgProfile
                )
                loadImageUrl(
                    imageURL = post.photo,
                    context = it,
                    width = 1200,
                    height = 1000,
                    imageView = postImg
                )
            }
        }
    }
    class PostViewHolder(binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val postBinding = binding
    }
}

