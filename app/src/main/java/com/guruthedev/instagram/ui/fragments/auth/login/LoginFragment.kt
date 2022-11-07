package com.guruthedev.instagram.ui.fragments.auth.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.guruthedev.instagram.MainActivity
import com.guruthedev.instagram.R
import com.guruthedev.instagram.databinding.FragmentLoginBinding
import com.guruthedev.instagram.extensions.getSpanValues
import com.guruthedev.instagram.extensions.showToast
import com.guruthedev.instagram.utils.LoginErrorType
import com.guruthedev.instagram.viewModel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initView()
        initListener()
    }

    private fun initObserver() {
        viewModel.taskResponseLiveData.observe(
            viewLifecycleOwner
        ) { taskResult ->
            (activity as MainActivity).navigateTo(actionId = R.id.action_loginFragment_to_reelsFragment)
        }
        viewModel.errorLiveData.observe(
            viewLifecycleOwner
        ) { loginError ->
            with(requireContext()) {
                when (loginError.loginErrorType) {
                    LoginErrorType.ERROR_EMPTY_EMAIL -> showToast(getString(R.string.error_empty_email))
                    LoginErrorType.ERROR_EMPTY_PASSWORD -> showToast(getString(R.string.error_empty_password))
                    LoginErrorType.ERROR_API -> {
                        loginError.errorMessage?.let { errorMessage ->
                            showToast(errorMessage)
                        }
                    }
                }
            }
        }
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
                viewModel.validateCred(email, password)
            }
        }
    }
}