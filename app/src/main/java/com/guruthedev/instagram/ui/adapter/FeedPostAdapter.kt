package com.guruthedev.instagram.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.Post
import com.guruthedev.instagram.databinding.PostListItemBinding

class FeedPostAdapter : RecyclerView.Adapter<FeedPostAdapter.MyViewHolder>() {
    private var items = ArrayList<Post>()
    fun setUpdatedData(items: ArrayList<Post>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PostListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = items[position]
        holder.binding.apply {
            instagramPost = post
            heartIcon.setOnClickListener {
                heartIcon.setImageResource(R.drawable.fav_fill)
            }
        }
    }

    override fun getItemCount() = items.size
}
