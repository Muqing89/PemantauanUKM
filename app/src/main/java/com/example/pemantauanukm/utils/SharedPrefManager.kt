package com.example.pemantauanukm.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveLogin(username: String, role: String) {
        prefs.edit { putString("username", username).putString("role", role) }
    }

    fun getRole(): String? {
        return prefs.getString("role", null)
    }

    fun logout() {
        prefs.edit { clear() }
    }
}
