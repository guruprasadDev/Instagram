package com.guruthedev.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.guruthedev.instagram.data.MessageEvent
import com.guruthedev.instagram.databinding.ActivityAccountSettingsBinding
import com.guruthedev.instagram.service.PassingData
import com.guruthedev.instagram.utils.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AccountSettingsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAccountSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_settings)

    }
}