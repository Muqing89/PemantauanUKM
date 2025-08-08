package com.example.pemantauanukm.model

sealed class UiState<out T> {
    object Loading : UiState<Nothing>() // State saat loading
    data class Success<T>(val data: T): UiState<T>() // State saat data berhasil diambil
    data class Error(val message: String): UiState<Nothing>() // State saat error
}
