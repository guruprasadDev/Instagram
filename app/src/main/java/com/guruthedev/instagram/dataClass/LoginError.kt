package com.guruthedev.instagram.dataClass

import com.guruthedev.instagram.utils.LoginErrorType

data class LoginError(val loginErrorType: LoginErrorType, val errorMessage: String? = null)
