package com.example.pemantauanukm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pemantauanukm.utils.SharedPrefManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val sharedPrefManager: SharedPrefManager) : ViewModel() {

    private val _loginState = MutableStateFlow<Boolean?>(null)  // null = belum login, true/false = hasil login
    val loginState: StateFlow<Boolean?> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            // Contoh login hardcoded
            if ((username == "ormawa" && password == "1234") || (username == "admin" && password == "admin")) {
                val role = if (username == "ormawa") "ormawa" else "admin"
                sharedPrefManager.saveLogin(username, role)
                _loginState.value = true
            } else {
                _loginState.value = false
            }
        }
    }
}
