package com.example.pemantauanukm.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.pemantauanukm.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val USERS_KEY = "registered_users"
        private const val LOGGED_IN_USER = "logged_in_user"
    }

    // Ambil semua user dari SharedPreferences
    private fun getAllUsers(): List<User> {
        val json = prefs.getString(USERS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    // Simpan list user ke SharedPreferences
    private fun saveAllUsers(users: List<User>) {
        val json = gson.toJson(users)
        prefs.edit { putString(USERS_KEY, json) }
    }

    // Simpan user baru
    fun saveUser(username: String, password: String) {
        val users = getAllUsers().toMutableList()
        users.add(User(username, password))
        saveAllUsers(users)
    }

    // Cek apakah username sudah digunakan
    fun isUsernameExist(username: String): Boolean {
        return getAllUsers().any { it.username == username }
    }

    // Cek login user
    fun checkUserLogin(inputUsername: String, inputPassword: String): Boolean {
        return getAllUsers().any { it.username == inputUsername && it.password == inputPassword }
    }

    // Simpan user yang sedang login
    fun saveLogin(username: String, password: String) {
        val user = getAllUsers().find { it.username == username && it.password == password }
        user?.let {
            val json = gson.toJson(it)
            prefs.edit { putString(LOGGED_IN_USER, json) }
        }
    }

    // Ambil data user yang sedang login
    fun getLoggedInUser(): User? {
        val json = prefs.getString(LOGGED_IN_USER, null)
        return json?.let { gson.fromJson(it, User::class.java) }
    }

    // Logout user
    fun logout() {
        prefs.edit { remove(LOGGED_IN_USER) }
    }
}
