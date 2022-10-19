package com.guruthedev.instagram.applicationClass

import android.app.Application
import com.guruthedev.instagram.SharedPreference

class GiApplication : Application() {
    private lateinit var preference: SharedPreference

    override fun onCreate() {
        super.onCreate()
        preference = SharedPreference(this)
    }
}