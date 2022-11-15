package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guruthedev.instagram.databinding.FragmentGalleryBinding
import com.guruthedev.instagram.ui.adapter.GalleryAdapter
import java.io.File

class GalleryFragment:Fragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       loadGalleryImage()
    }

    private fun loadGalleryImage() {
        val directory = File(context?.externalMediaDirs?.get(0)?.absolutePath ?: "")
        val files = directory.listFiles() as Array<File>
        val adapter = GalleryAdapter(files.reversedArray())
        binding.viewPager.adapter = adapter
    }
}