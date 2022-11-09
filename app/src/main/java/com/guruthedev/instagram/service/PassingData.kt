package com.guruthedev.instagram.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.guruthedev.instagram.data.MessageEvent
import com.guruthedev.instagram.utils.Constants
import org.greenrobot.eventbus.EventBus

class PassingData : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val fullName = intent?.getStringExtra(Constants.FULL_NAME) ?: ""
        val username = intent?.getStringExtra(Constants.USERNAME) ?: ""
        val bio = intent?.getStringExtra(Constants.BIO) ?: ""
        EventBus.getDefault().post(MessageEvent(fullName, username, bio))
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}