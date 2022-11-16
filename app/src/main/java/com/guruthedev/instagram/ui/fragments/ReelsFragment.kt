package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.guruthedev.instagram.databinding.FragmentReelsBinding
import com.guruthedev.instagram.ui.adapter.ReelsAdapter
import com.guruthedev.instagram.viewModel.ReelsFragmentViewModel

class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
    private lateinit var reelsAdapter: ReelsAdapter
    private lateinit var viewModel: ReelsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ReelsFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        reelsAdapter = ReelsAdapter(viewModel.getOptionsFromDatabase())
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