package com.guruthedev.instagram.network

import android.content.Context
import android.util.Log

class NetworkSyncer(context: Context) : NetworkService {
    private val networkManager = NetworkManager(context)

    fun start() {
        networkManager.addNetworkCallback(this)
    }

    override fun onNetworkChanged(networkState: NetworkState) {
        Log.d("NetworkSyncer", networkState.toString())
    }
}