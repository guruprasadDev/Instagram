package com.guruthedev.instagram.applicationClass

import android.app.Application
import com.guruthedev.instagram.preference.Prefs


val prefs: Prefs by lazy {
    ApplicationInitialize.prefs!!
}

class ApplicationInitialize : Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var initializer: ApplicationInitialize
            private set
        //set means it will perform default initialization
    }

    override fun onCreate() {
        super.onCreate()
        initializer = this
        prefs = Prefs(applicationContext)
        prefs!!.intExamplePref =10
    }
}