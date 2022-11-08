package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.ExoPlayerItem
import com.guruthedev.instagram.data.Reels
import com.guruthedev.instagram.databinding.FragmentReelsBinding
import com.guruthedev.instagram.ui.adapter.VideoAdapter

class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
    private lateinit var adapter: VideoAdapter
    private val videos = ArrayList<Reels>()
    private val exoPlayerItems = ArrayList<ExoPlayerItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playVideos()
        registerPageCallBack()
        videoInitListener()
    }

    private fun videoInitListener() {

        adapter =
            VideoAdapter(requireContext(), videos, object : VideoAdapter.OnVideoPreparedListener {
                override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                    exoPlayerItems.add(exoPlayerItem)
                }
            })
        binding.viewPager2.adapter = adapter
    }

    private fun registerPageCallBack() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position:Int) {
                val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                if (previousIndex != -1) {
                    val player = exoPlayerItems[previousIndex].exoPlayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1) {
                    val player = exoPlayerItems[newIndex].exoPlayer
                    player.playWhenReady = true
                    player.play()
                }
            }
        })
    }

    private fun playVideos() {
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )

        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
        videos.add(
            Reels(
                getString(R.string.videos_title),
                getString(R.string.video_url),
                getString(R.string.img_url),
                getString(R.string.video_likes_count),
                getString(R.string.video_comment_count),
                getString(R.string.video_des),
            )
        )
    }

    override fun onPause() {
        super.onPause()

        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.pause()
            player.playWhenReady = false
        }
    }

    override fun onResume() {
        super.onResume()

        val index = exoPlayerItems.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if (index != -1) {
            val player = exoPlayerItems[index].exoPlayer
            player.playWhenReady = true
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (exoPlayerItems.isNotEmpty()) {
            for (item in exoPlayerItems) {
                val player = item.exoPlayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }
}