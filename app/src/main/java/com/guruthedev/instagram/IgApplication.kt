package com.guruthedev.instagram

import android.app.Application
import android.content.Context
import com.guruthedev.instagram.data.pref.IgPreference
import com.guruthedev.instagram.network.NetworkSyncer

class IgApplication : Application() {
    private lateinit var preferences: IgPreference

    companion object {
        lateinit var instances: IgApplication
            private set

        fun applicationContext(): Context {
            return instances.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instances = this
        preferences = IgPreference(this)
        val context: Context = applicationContext()
        val networkSyncer = NetworkSyncer(context)
        networkSyncer.start()
    }

    fun getPreference(): IgPreference = preferences
}