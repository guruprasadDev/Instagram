package com.guruthedev.instagram.ui.fragments.auth

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.guruthedev.instagram.MainActivity
import com.guruthedev.instagram.R
import com.guruthedev.instagram.databinding.FragmentLoginBinding
import com.guruthedev.instagram.extensions.getSpanValues

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        initView()
        initListener()
    }

    private fun initView() {
        binding.signUpBtnTxt.apply {
            setText(getLoginBtnText(), TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun getLoginBtnText(): SpannableString {
        val text = getString(R.string.don_t_have_an_account_txt)
        val clickableText = getString(R.string.sign_up_txt)
        val logInText = text.plus(clickableText)
        return logInText.getSpanValues(text, clickableText) {
            (activity as MainActivity).navigateTo(actionId = R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun initListener() {
        binding.apply {
            loginBtn.setOnClickListener {
                val email = emailEdt.text.toString().trim()
                val password = passwordEdt.text.toString().trim()
                validateCred(email, password)
            }
        }
    }

    private fun validateCred(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { taskResult ->
                    if (!taskResult.isSuccessful) {
                        (activity as MainActivity).navigateTo(actionId = R.id.action_loginFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            activity,
                            taskResult.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(activity, getString(R.string.toast_for_login_message), Toast.LENGTH_SHORT).show()
        }
    }
}