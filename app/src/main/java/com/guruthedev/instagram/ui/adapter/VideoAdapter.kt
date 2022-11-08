package com.guruthedev.instagram.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.ExoPlayerItem
import com.guruthedev.instagram.data.Reels
import com.guruthedev.instagram.databinding.VideoListItemBinding
import com.guruthedev.instagram.utils.loadImageUrlLogo

class VideoAdapter(
    var context: Context,
    var videos: ArrayList<Reels>,
    var videoPreparedListener: OnVideoPreparedListener
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = VideoListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return VideoViewHolder(view, context, videoPreparedListener)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val model = videos[position]
        holder.setVideoPath(model.url)
        holder.binding.apply {
            tvUsername.text = model.tittle
            tvLikesTapCount.text = model.likes
            tvComments.text = model.comments
            tvDescription.text = model.description
            loadImageUrlLogo(
                imageURL = model.logo,
                context = context,
                width = 800,
                height = 400,
                imageView = imgProfile
            )
        }
    }

    interface OnVideoPreparedListener {
        fun onVideoPrepared(exoPlayerItem: ExoPlayerItem)
    }

    override fun getItemCount() = videos.size
}

