package com.guruthedev.instagram.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.Reels
import com.guruthedev.instagram.databinding.VideoListItemBinding

class ReelsAdapter(options: FirebaseRecyclerOptions<Reels?>) :
    FirebaseRecyclerAdapter<Reels?, ReelsAdapter.MyViewHolder?>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            VideoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Reels) {
        holder.setData(model, holder.itemView.context)
    }

    inner class MyViewHolder(val binding: VideoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(reel: Reels, context: Context) {
            binding.apply {
                videoView.setVideoPath(reel.url)
                tvUsername.text = reel.title
                tvLikesTapCount.text = reel.likes
                tvComments.text = reel.comments.toString()
                tvDescription.text = reel.desc
                videoView.setOnPreparedListener { mediaPlayer ->
                    pbLoading.visibility = View.GONE
                    mediaPlayer.start()
                    videoView.setOnCompletionListener { mediaPlayer.start() }
                    with(HeartIcon) {
                        setOnClickListener {
                            setImageResource(R.drawable.fav_fill)
                            setColorFilter(ContextCompat.getColor(context, R.color.red))
                        }
                    }
                }
            }
        }
    }
}