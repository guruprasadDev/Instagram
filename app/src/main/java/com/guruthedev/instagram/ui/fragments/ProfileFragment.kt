package com.guruthedev.instagram.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guruthedev.instagram.AccountSettingsActivity
import com.guruthedev.instagram.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editAccountSettingBtn.setOnClickListener {
            startActivity(Intent(context,AccountSettingsActivity::class.java))
        }

        return binding.root
    }
}