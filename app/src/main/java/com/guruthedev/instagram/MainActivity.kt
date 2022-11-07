package com.guruthedev.instagram

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.guruthedev.instagram.databinding.ActivityMainBinding
import com.guruthedev.instagram.ui.fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val cm: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                @SuppressLint("ShowToast")
                override fun onAvailable(network: Network) {
                    Log.i("MainActivity", "onAvailable!")
                    // check if NetworkCapabilities has TRANSPORT_WIFI
                    val isWifi: Boolean = cm.getNetworkCapabilities(network)?.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ) == true
                }

                override fun onLost(network: Network) {
                    Log.i("MainActivity", "onLost!")
                    val snack = Snackbar.make(
                        binding.container,
                        "Connect to Internet",
                        Snackbar.LENGTH_LONG
                    )
                    snack.show()
                }
            }
        )

        loadFragment(fragment = Fragment())
        binding.navView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.nav_search -> {
                    loadFragment(SearchFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.nav_post -> {
                    loadFragment(PostFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.nav_notifications -> {
                    loadFragment(NotificationFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun navigateTo(actionId: Int) {
        findNavController(R.id.main_nav_host_container).navigate(actionId)
    }

    fun updateBottomNavVisibility(show: Boolean) {
        binding.navView.visibility = if (show) VISIBLE else GONE
    }
}