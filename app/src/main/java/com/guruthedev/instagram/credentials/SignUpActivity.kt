package com.guruthedev.instagram.credentials

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.guruthedev.instagram.R
import com.guruthedev.instagram.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)


        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        val text = "Have an account? Log in."
        val spannableString = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                binding.LoginBtn?.setOnClickListener {
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(clickableSpan, 16, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val boldSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(boldSpan, 16, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.apply {
            LoginBtn?.let {
                LoginBtn.setText(spannableString, TextView.BufferType.SPANNABLE)
                LoginBtn.movementMethod = LinkMovementMethod.getInstance()
                LoginBtn.highlightColor = Color.TRANSPARENT
            }
        }

    }
}
