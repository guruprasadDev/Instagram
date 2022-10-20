package com.guruthedev.instagram.data.pref

import com.guruthedev.instagram.data.pref.IgPreference.Companion.IS_LOGIN
import com.guruthedev.instagram.data.pref.IgPreference.Companion.KEY_EMAIL
import com.guruthedev.instagram.data.pref.IgPreference.Companion.KEY_FULL_NAME
import com.guruthedev.instagram.data.pref.IgPreference.Companion.KEY_USERNAME

object SessionPrefHelper {

    fun saveEmailPostLogin(
        igPreference: IgPreference,
        email: String
    ) {
        igPreference.editor.apply {
            putBoolean(IS_LOGIN, true)
            putString(KEY_EMAIL, email)
            commit()
        }
    }

    fun getUserDetails(igPreference: IgPreference): HashMap<String, String?> {
        val user: Map<String, String?> = HashMap<String, String>()
        with(igPreference) {
            (user as HashMap)[KEY_FULL_NAME] = preference.getString(KEY_FULL_NAME, "")
            (user)[KEY_USERNAME] = preference.getString(KEY_USERNAME, "")
            (user as HashMap)[KEY_EMAIL] = preference.getString(KEY_EMAIL, "")
            return user
        }
    }

    fun logoutUser(igPreference: IgPreference) {
        igPreference.editor.apply {
            clear()
            commit()
        }
    }

    fun isLoggedIn(igPreference: IgPreference): Boolean =
        igPreference.preference.getBoolean(IS_LOGIN, false)
}