package com.nakul.template.database.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class PreferenceImpl @Inject constructor(
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) {
    fun storeKey(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    fun storeBoolKey(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getKey(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun getBoolKey(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun <T> storeObject(key: String, data: T) {
        editor.putString(key, Gson().toJson(data))
        editor.apply()
    }

    fun <T> retrieveObject(key: String, clazz: Class<T>): T? {
        return try {
            Gson().fromJson(key, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun clearPreference() {
        editor.clear()
        editor.apply()
    }
}