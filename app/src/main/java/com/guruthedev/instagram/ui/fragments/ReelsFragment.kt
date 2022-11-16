package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.guruthedev.instagram.data.Reels
import com.guruthedev.instagram.databinding.FragmentReelsBinding
import com.guruthedev.instagram.ui.adapter.ReelsAdapter

class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
    private lateinit var reelsAdapter: ReelsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatabase()

    }

    private fun initDatabase() {
        val dataBase = Firebase.database.getReference("videos")
        val options = FirebaseRecyclerOptions.Builder<Reels>()
            .setQuery(dataBase, Reels::class.java)
            .build()

        reelsAdapter = ReelsAdapter(options)
        binding.viewPager2.adapter = reelsAdapter
    }

    override fun onStart() {
        super.onStart()
        reelsAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        reelsAdapter.stopListening()
    }
}