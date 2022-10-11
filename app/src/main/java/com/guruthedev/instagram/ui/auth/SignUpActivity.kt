package com.guruthedev.instagram.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.guruthedev.instagram.R
import com.guruthedev.instagram.databinding.ActivitySignUpBinding
import com.guruthedev.instagram.extensions.getSpanValues

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)


        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val text = "Have an account? "
        val clickableText = "Log in."
        val logInText = text.plus(clickableText)
        val spannableText = logInText.getSpanValues(text, clickableText, Color.RED) {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }
        binding.loginBtnTxt.apply {
            setText(spannableText, TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
}
