package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guruthedev.instagram.databinding.FragmentReelsBinding
import com.guruthedev.instagram.ui.adapter.ReelsAdapter
import com.guruthedev.instagram.viewModel.ReelsFragmentViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ReelsFragment : DaggerFragment() {
    private lateinit var binding: FragmentReelsBinding

    lateinit var reelsAdapter: ReelsAdapter

    @Inject
    lateinit var viewModel: ReelsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        //    viewModel = ViewModelProvider(this).get(ReelsFragmentViewModel::class.java)
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
