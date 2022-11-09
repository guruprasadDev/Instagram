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
    private lateinit var binding: ActivityAccountSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_settings)
        EventBus.getDefault().register(this)
        binding.addBtn.setOnClickListener {
            val intent = Intent(this, PassingData::class.java)
            intent.apply {
                putExtra(Constants.FULL_NAME, binding.fullNameProfileFrag.text.toString())
                putExtra(Constants.USERNAME, binding.usernameProfileFrag.text.toString())
                putExtra(Constants.BIO, binding.bioProfileFrag.text.toString())
                startService(intent)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(messageEvent: MessageEvent) {
        binding.apply {
            tvFullName.text = messageEvent.fullName
            tvUsername.text = messageEvent.username
            tvBio.text = messageEvent.bio
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}