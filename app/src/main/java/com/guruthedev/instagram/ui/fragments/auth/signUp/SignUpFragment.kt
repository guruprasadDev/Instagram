package com.guruthedev.instagram.ui.fragments.auth.signUp

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
import com.guruthedev.instagram.databinding.FragmentSignUpBinding
import com.guruthedev.instagram.extensions.getSpanValues
import com.guruthedev.instagram.extensions.showToast
import com.guruthedev.instagram.utils.SignUpErrorType
import com.guruthedev.instagram.viewModel.SignUpViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
        initListener()
    }

    private fun initObservers() {
        viewModel.taskResponseLiveData.observe(viewLifecycleOwner) { taskResult ->
            (activity as MainActivity).navigateTo(actionId = R.id.action_signUpFragment_to_homeFragment)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { signUpError ->
            with(requireContext()) {
                when (signUpError.signUpTypeError) {
                    SignUpErrorType.ERROR_EMPTY_FULL_NAME -> showToast(getString(R.string.error_empty_full_name))
                    SignUpErrorType.ERROR_EMPTY_USERNAME -> showToast(getString(R.string.error_empty_username))
                    SignUpErrorType.ERROR_EMPTY_EMAIL -> showToast(getString(R.string.error_empty_email))
                    SignUpErrorType.ERROR_EMPTY_PASSWORD -> showToast(getString(R.string.error_empty_password))
                    SignUpErrorType.ERROR_API -> {
                        signUpError.errorMessage?.let { errorMessage ->
                            showToast(errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.loginBtnTxt.apply {
            setText(getSignUpText(), TextView.BufferType.SPANNABLE)
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun getSignUpText(): SpannableString {
        val text = getString(R.string.login_have_an_account_txt)
        val clickableText = getString(R.string.login_txt)
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
                viewModel.validateCred(fullName, username, email, password)
            }
        }
    }
}