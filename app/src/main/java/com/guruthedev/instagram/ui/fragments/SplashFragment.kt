package com.guruthedev.instagram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.guruthedev.instagram.IgApplication
import com.guruthedev.instagram.MainActivity
import com.guruthedev.instagram.R
import com.guruthedev.instagram.data.pref.IgPreference.Companion.IS_LOGIN
import com.guruthedev.instagram.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val igPreference = IgApplication.instances.getPreference()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as MainActivity
        var isLoggedIn: String?
        //if (SessionPrefHelper.isLoggedIn(igPreference)) {
        lifecycleScope.launch {
            isLoggedIn = igPreference.read(IS_LOGIN)
            if (isLoggedIn == "true") {
                activity.navigateTo(R.id.action_splashFragment_to_homeFragment2)
                activity.updateBottomNavVisibility(true)
            } else {
                activity.navigateTo(R.id.action_splashFragment_to_signUpFragment)
                activity.updateBottomNavVisibility(false)
            }
        }
    }
}