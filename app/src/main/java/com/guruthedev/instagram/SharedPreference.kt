package com.guruthedev.instagram

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.guruthedev.instagram.ui.fragments.auth.login.LoginFragment
import com.guruthedev.instagram.utils.Constants.IS_LOGIN
import com.guruthedev.instagram.utils.Constants.KEY_EMAIL
import com.guruthedev.instagram.utils.Constants.KEY_FULL_NAME
import com.guruthedev.instagram.utils.Constants.KEY_USERNAME
import com.guruthedev.instagram.utils.Constants.PREF_NAME

class SharedPreference {
    private lateinit var preference: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    lateinit var context: Context

    constructor(context: Context) {
        this.context = context
        preference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = preference.edit()
    }

    fun createLoginSession(fullName: String, username: String, email: String) {
        editor.apply {
            putBoolean(IS_LOGIN, true)
            putString(KEY_FULL_NAME, fullName)
            putString(KEY_USERNAME, username)
            putString(KEY_EMAIL, email)
            commit()
        }
    }

    fun checkSession() {
        if (!this.isLoggedIn()) {
            //here how can we use navigation LoginFragment
        }
    }

    fun getUserDetails(): HashMap<String, String> {
        val user: Map<String, String> = HashMap<String, String>()
        (user as HashMap)[KEY_FULL_NAME] = preference.getString(KEY_FULL_NAME, null)!!
        (user as HashMap)[KEY_USERNAME] = preference.getString(KEY_USERNAME, null)!!
        (user as HashMap)[KEY_EMAIL] = preference.getString(KEY_EMAIL, null)!!
        return user
    }

    fun logoutUser() {
        editor.apply {
            clear()
            commit()
            val intent = Intent(context, LoginFragment::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    fun isLoggedIn(): Boolean {
        return preference.getBoolean(IS_LOGIN, false)
    }
}