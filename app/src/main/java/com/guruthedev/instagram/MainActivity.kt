package com.guruthedev.instagram

import ConnectionLiveData
import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.guruthedev.instagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var connectionLiveData: ConnectionLiveData


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObserver()
    }

    private fun initObserver() {
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                updateUI()
            }
        }
    }

    fun navigateTo(actionId: Int) {
        findNavController(R.id.main_nav_host_container).navigate(actionId)
    }

    fun updateBottomNavVisibility(show: Boolean) {
        binding.navView.visibility = if (show) VISIBLE else GONE
    }

    private fun updateUI() {
        val snack = Snackbar.make(
            binding.container,
            "Connected to Internet",
            Snackbar.LENGTH_LONG,
        )
        snack.show()
    }
}