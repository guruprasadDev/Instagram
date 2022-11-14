package com.guruthedev.instagram.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guruthedev.instagram.AccountSettingsActivity
import com.guruthedev.instagram.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editAccountSettingBtn.setOnClickListener {
            startActivity(Intent(context, AccountSettingsActivity::class.java))
        }
        val args = this.arguments
        val fullName = args?.get("full_name")
        val username = args?.get("username")
        val bio = args?.get("bio")
        binding.fullNameProfileFrag.text = fullName.toString()
        binding.profileFragmentUsername.text = username.toString()
        binding.bioProfileFrag.text = bio.toString()

        return binding.root
    }
}