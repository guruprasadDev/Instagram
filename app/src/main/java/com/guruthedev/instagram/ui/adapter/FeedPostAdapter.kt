package com.guruthedev.instagram.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.Post
import com.guruthedev.instagram.databinding.PostListItemBinding

class FeedPostAdapter : RecyclerView.Adapter<FeedPostAdapter.MyViewHolder>() {
    private lateinit var binding: PostListItemBinding
    private var items = ArrayList<Post>()
    fun setUpdatedData(items: ArrayList<Post>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = items[position]
        val fav = binding.HeartIcon
        var isFav = false

        binding.instagramPost = post
        fav.setOnClickListener {
            fav.setImageResource(R.drawable.fav_fill)
            isFav = true
        }
    }

    override fun getItemCount() = items.size
}
