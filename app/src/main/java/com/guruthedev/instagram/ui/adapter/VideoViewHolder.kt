package com.guruthedev.instagram.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.ExoPlayerItem
import com.guruthedev.instagram.databinding.VideoListItemBinding

class VideoViewHolder(
    val binding: VideoListItemBinding,
    var context: Context,
    var videoPreparedListener: VideoAdapter.OnVideoPreparedListener
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mediaSource: MediaSource

    fun setVideoPath(url: String) {
        exoPlayer = ExoPlayer.Builder(context).build()
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Toast.makeText(context, context.getString(R.string.video_error_message), Toast.LENGTH_SHORT).show()
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == Player.STATE_BUFFERING) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else if (playbackState == Player.STATE_READY) {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })
        binding.playerView.player = exoPlayer
        exoPlayer.seekTo(0)
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
        val dataSourceFactory = DefaultDataSource.Factory(context)
        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
        if (absoluteAdapterPosition == 0) {
            exoPlayer.playWhenReady = true
            exoPlayer.play()
        }
        videoPreparedListener.onVideoPrepared(ExoPlayerItem(exoPlayer, absoluteAdapterPosition))
    }
}