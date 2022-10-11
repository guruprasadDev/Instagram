package com.guruthedev.instagram.ui.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.guruthedev.instagram.R
import com.guruthedev.instagram.databinding.ActivityLoginBinding
import com.guruthedev.instagram.extensions.getSpanValues

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val text = "Don't have an account? "
        val clickableText = "Sign up now!"
        val signUpText = text.plus(clickableText)
        val spanAbleText = signUpText.getSpanValues(text, clickableText, Color.RED) {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
        binding.signUpTxtBtn.apply {
            setText(spanAbleText, TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }
}