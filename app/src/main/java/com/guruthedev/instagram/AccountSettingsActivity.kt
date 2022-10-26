package com.guruthedev.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.guruthedev.instagram.data.ResultData
import com.guruthedev.instagram.databinding.ActivityAccountSettingsBinding
import com.guruthedev.instagram.service.PassingData
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AccountSettingsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAccountSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_account_settings)
        EventBus.getDefault().register(this)
        binding.addBtn.setOnClickListener {
                val intent = Intent(this,PassingData::class.java)
                intent.putExtra("full_name",binding.fullNameProfileFrag.text.toString())
                intent.putExtra("username",binding.usernameProfileFrag.text.toString())
                intent.putExtra("bio",binding.bioProfileFrag.text.toString())
                startService(intent)
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onEvent(resultData: ResultData){
        binding.fullNameTxt.text=resultData.fullName
        binding.usernameTxt.text=resultData.username
        binding.bioTxt.text=resultData.bio
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}