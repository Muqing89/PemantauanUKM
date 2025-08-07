package com.example.pemantauanukm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pemantauanukm.utils.SharedPrefManager

class LoginViewModelFactory(
    private val sharedPrefManager: SharedPrefManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(sharedPrefManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
