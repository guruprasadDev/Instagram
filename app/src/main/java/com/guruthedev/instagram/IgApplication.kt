package com.guruthedev.instagram

import android.app.Application
import com.guruthedev.instagram.data.pref.IgPreference

class IgApplication : Application() {
    private lateinit var preferences: IgPreference
    companion object {
        lateinit var instances: IgApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instances = this
        preferences = IgPreference(this)
    }

    fun getPreference(): IgPreference = preferences
}