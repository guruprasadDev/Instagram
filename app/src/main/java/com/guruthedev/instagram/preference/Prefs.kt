package com.guruthedev.instagram.preference

import android.content.Context
import android.content.SharedPreferences
import com.guruthedev.instagram.R
import com.guruthedev.instagram.applicationClass.prefs

class Prefs(context: Context) {
    private val APP_PREF = context.getString(R.string.app_preference)
    private val APP_PREF_INT_EXAMPLE = context.getString(R.string.int_example_preference)

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREF,Context.MODE_PRIVATE)
    var intExamplePref: Int
        get() = preferences.getInt(APP_PREF_INT_EXAMPLE, -1)
        set(value) = preferences.edit().putInt(APP_PREF_INT_EXAMPLE, value).apply()
    //getter
    val examplePref = prefs.intExamplePref
}

