package com.guruthedev.instagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.guruthedev.instagram.databinding.ActivityMainBinding
import com.guruthedev.instagram.ui.fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedFragment: Fragment? = null

    private val onNavigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            selectedFragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_search -> SearchFragment()
                R.id.nav_post -> PostFragment()
                R.id.nav_notifications -> NotificationFragment()
                R.id.navigation_profile -> ProfileFragment()
                else -> {
                    TODO()
                }
            }
            navigateTo()
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navView.setOnItemSelectedListener(onNavigationItemSelectedListener)
        selectedFragment = HomeFragment()
        navigateTo()
    }

    private fun navigateTo() {
        selectedFragment?.let { fragment ->
            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                fragment
            ).commit()
        }
    }
}