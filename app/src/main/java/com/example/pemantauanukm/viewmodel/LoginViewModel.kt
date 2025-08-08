package com.example.pemantauanukm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pemantauanukm.utils.SharedPrefManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val sharedPrefManager: SharedPrefManager) : ViewModel() {

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    fun login(username: String, password: String) {
        if (sharedPrefManager.checkUserLogin(username, password)) {
            sharedPrefManager.saveLogin(username, password)
            _loginState.value = true
        } else {
            _loginState.value = false
        }
    }

    // Tambahan: reset state agar tidak terus trigger LaunchedEffect
    fun resetLoginState() {
        _loginState.value = null
    }
}
