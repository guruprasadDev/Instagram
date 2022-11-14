package com.guruthedev.instagram.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Build.VERSION_CODES


class NetworkManager(context: Context) {

    private val connectivityManager: ConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private var network: Network? = null
    private val networkCallbacks: HashSet<NetworkService> = hashSetOf()
    private val networkCapabilities: NetworkCapabilities?
        get() = connectivityManager.getNetworkCapabilities(network)
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            this@NetworkManager.network = network
            updateCallbacks(availableNetwork())
        }

        override fun onLost(network: Network) {
            if (this@NetworkManager.network == network) {
                updateCallbacks(NetworkState(false, NetworkType.UNKNOWN))
            }
        }
    }

    init {
        registerNetworkCallback()
    }

    fun addNetworkCallback(networkService: NetworkService) {
        networkCallbacks.add(networkService)
    }

    fun removeNetworkCallback(networkService: NetworkService) {
        networkCallbacks.remove(networkService)
    }

    fun updateCallbacks(state: NetworkState) {
        networkCallbacks.forEach { networkService ->
            networkService.onNetworkChanged(state)
        }
    }

    fun availableNetwork(): NetworkState {
        val networkState =
                if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true) {
                    NetworkState(true, NetworkType.CELLULAR)
                } else if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                    NetworkState(true, NetworkType.WIFI)
                } else {
                    NetworkState(true, NetworkType.UNKNOWN)
                }
        return networkState
    }

    private fun registerNetworkCallback() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            // if SDK version is lower than N
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
        }
    }
}

data class NetworkState(
        val isConnected: Boolean,
        val networkType: NetworkType
)

enum class NetworkType {
    CELLULAR,
    WIFI,
    UNKNOWN;
}

interface NetworkService {
    fun onNetworkChanged(networkState: NetworkState)
}