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
    private lateinit var binding: VideoListItemBinding
    var isFav = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = VideoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Reels) {
        holder.setData(model, holder.itemView.context)
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun setData(obj: Reels, context: Context) {
            binding.apply {
                videoView.setVideoPath(obj.url)
                tvUsername.text = obj.tittle
                tvLikesTapCount.text = obj.likes
                tvComments.text = obj.comments.toString()
                tvDescription.text = obj.desc
                videoView.setOnPreparedListener { mediaPlayer ->
                    pbLoading.visibility = View.GONE
                    mediaPlayer.start()
                    videoView.setOnCompletionListener { mediaPlayer -> mediaPlayer.start() }
                    with(HeartIcon) {
                        setOnClickListener {
                            setImageResource(R.drawable.fav_fill)
                            setColorFilter(ContextCompat.getColor(context, R.color.red))
                        }
                        isFav = true
                    }
                }
            }
        }
    }
}