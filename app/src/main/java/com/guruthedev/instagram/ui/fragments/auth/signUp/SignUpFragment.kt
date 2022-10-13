package com.guruthedev.instagram.ui.fragments.auth.signUp

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
import com.guruthedev.instagram.databinding.FragmentSignUpBinding
import com.guruthedev.instagram.extensions.getSpanValues

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        initView()
        initListener()
    }

    private fun initView() {
        binding.loginBtnTxt.apply {
            setText(getSignUpText(), TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun getSignUpText(): SpannableString {
        val text = getString(R.string.don_t_have_an_account_txt)
        val clickableText = getString(R.string.sign_up_txt)
        val logInText = text.plus(clickableText)
        return logInText.getSpanValues(text, clickableText) {
            (activity as MainActivity).navigateTo(actionId = R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun initListener() {
        binding.apply {
            signUpBtn.setOnClickListener {
                val fullName = fullNameEdt.text.toString().trim()
                val username = usernameEdt.text.toString().trim()
                val email = emailEdt.text.toString().trim()
                val password = passwordEdt.text.toString().trim()
                validateCred(fullName, username, email, password)
            }
        }
    }

    private fun validateCred(fullName: String, username: String, email: String, password: String) {
        if (fullName.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        (activity as MainActivity).navigateTo(actionId = R.id.action_signUpFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            activity,
                            taskResult.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(
                activity,
                getString(R.string.toast_for_login_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}









