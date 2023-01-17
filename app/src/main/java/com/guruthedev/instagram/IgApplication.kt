package com.guruthedev.instagram

import android.app.Application
import com.guruthedev.instagram.data.pref.IgPreference
import com.guruthedev.instagram.di.DaggerAppComponent
import com.guruthedev.instagram.network.NetworkSyncer
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class IgApplication : DaggerApplication() {
    private lateinit var preferences: IgPreference
    var isConnectedToInternet: Boolean = false

    companion object {
        lateinit var instances: IgApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instances = this
        preferences = IgPreference(this)
        val networkSyncer = NetworkSyncer(applicationContext)
        networkSyncer.start()
        networkSyncer.currentNetworkState.observeForever { networkState ->
            isConnectedToInternet = networkState.isConnected
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().createApp(this)
    }

    fun getPreference(): IgPreference = preferences
}