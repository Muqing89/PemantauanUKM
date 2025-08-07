package com.example.pemantauanukm.utils

object Validator {
    fun isValidUsername(username: String): Boolean {
        return username.length >= 4
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isNotEmpty(text: String): Boolean {
        return text.trim().isNotEmpty()
    }
}
