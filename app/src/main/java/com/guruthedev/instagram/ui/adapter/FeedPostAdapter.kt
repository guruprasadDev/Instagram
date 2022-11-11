package com.guruthedev.instagram.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.RecyclerData
import com.guruthedev.instagram.databinding.PostListItemBinding
import com.squareup.picasso.Picasso

class FeedPostAdapter : RecyclerView.Adapter<FeedPostAdapter.MyViewHolder>() {
    private lateinit var binding: PostListItemBinding
    private var items = ArrayList<RecyclerData>()
    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val postImage = binding.postImg
        private val tvUserName = binding.tvUsername
        private val tvDesc = binding.descriptionTxt
        private val fav = binding.HeartIcon
        private var isFav = false


        fun bind(data: RecyclerData,context: Context) {
            tvUserName.text = data.name
            tvDesc.text = data.description
            val url = data.owner.avatar_url
            Picasso.get()
                .load(url)
                .into(postImage)

            fav.setOnClickListener {
                fav.setImageResource(R.drawable.fav_fill)
                fav.setColorFilter(ContextCompat.getColor(context, R.color.red))
                isFav = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position),holder.itemView.context)
        val post = items[position]
        binding.instagramPost = post

    }

    override fun getItemCount() = items.size
}
