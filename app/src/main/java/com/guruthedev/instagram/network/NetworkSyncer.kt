package com.guruthedev.instagram.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkSyncer(context: Context) : NetworkService {
    private val networkManager = NetworkManager(context)

    private var _currentNetworkState = MutableLiveData<NetworkState>()
    val currentNetworkState: LiveData<NetworkState> get() = _currentNetworkState

    fun start() {
        networkManager.addNetworkCallback(this)
    }

    override fun onNetworkChanged(networkState: NetworkState) {
        _currentNetworkState.postValue(networkState)
        Log.d("NetworkSyncer", networkState.toString())
    }
}