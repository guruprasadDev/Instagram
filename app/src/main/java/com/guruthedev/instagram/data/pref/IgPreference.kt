package com.guruthedev.instagram.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.guruthedev.instagram.IgApplication

class IgPreference(context: IgApplication) {
    var preference: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = preference.edit()

    companion object {
        const val PREF_NAME = "IG_PREFERENCE"
        const val IS_LOGIN = "IS_SIGN"
        const val KEY_FULL_NAME = "FULL_NAME"
        const val KEY_USERNAME = "USERNAME"
        const val KEY_EMAIL = "EMAIL"
    }
}