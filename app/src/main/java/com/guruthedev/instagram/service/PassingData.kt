package com.guruthedev.instagram.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.guruthedev.instagram.data.ResultData
import org.greenrobot.eventbus.EventBus

class PassingData : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val fullName = intent?.getStringExtra("full_name")
        val username = intent?.getStringExtra("username")
        val bio = intent?.getStringExtra("bio")
        EventBus.getDefault().post(ResultData(fullName!!,username!!,bio!!))
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}