package com.guruthedev.instagram.dataClass

import com.guruthedev.instagram.utils.SignUpErrorType

data class SignUpError(val signUpTypeError: SignUpErrorType, val errorMessage: String? = null)
