package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
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
            override fun onPageSelected(position: Int) {
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
                "its_me_guru_reddy",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "600k",
                "22k",
                "Forest fun",
            )
        )

        videos.add(
            Reels(
                "iam_sai_kumar",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "789k",
                "360",
                "beautiful video"
            )
        )

        videos.add(
            Reels(
                "vishnu.8",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
            "229k",
                "66",
                "hey lets dream big",
            )
        )
        videos.add(
            Reels(
                "iam_venu",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "788k",
                "98k",
                "success makes life happier",
            )
        )
        videos.add(
            Reels(
                "itz_me_chandu",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "98",
                "55",
                "great day",
            )
        )
        videos.add(
            Reels(
                "Elon_Musk",
                "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "219k",
                "99k",
                "life line",
            )
        )
        videos.add(
            Reels(
                "guru21",
                "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "123k",
                "90k",
                "nice work",
            )
        )
        videos.add(
            Reels(
                "GV_Instance",
                "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "890K",
                "345",
                "hey",
            )
        )
        videos.add(
            Reels(
                "For Bigger Escape",
                "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                "https://ca.slack-edge.com/T02TLUWLZ-U040DBX2XUG-0a35a4f5f560-512",
                "901k",
                "297k",
                "programming",
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