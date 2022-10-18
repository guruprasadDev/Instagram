package com.guruthedev.instagram.data

import com.guruthedev.instagram.utils.LoginErrorType

data class LoginError(val loginErrorType: LoginErrorType, val errorMessage: String? = null)
