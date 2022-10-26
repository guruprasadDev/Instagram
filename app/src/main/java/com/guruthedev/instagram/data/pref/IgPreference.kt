package com.guruthedev.instagram.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.guruthedev.instagram.IgApplication
import kotlinx.coroutines.flow.first

class IgPreference(val context: IgApplication) {
    var preference: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = preference.edit()
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

     suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit {  user ->
            user[dataStoreKey] = value
        }
    }

     suspend fun read(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preference = context.dataStore.data.first()
        return preference[dataStoreKey]
    }

    companion object {
        const val PREF_NAME = "IG_PREFERENCE"
        const val IS_LOGIN = "IS_SIGN"
        const val KEY_FULL_NAME = "FULL_NAME"
        const val KEY_USERNAME = "USERNAME"
        const val KEY_EMAIL = "EMAIL"
    }
}