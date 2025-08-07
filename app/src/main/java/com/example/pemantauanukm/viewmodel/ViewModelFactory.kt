package com.example.pemantauanukm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pemantauanukm.data.local.database.KegiatanRepository

class KegiatanViewModelFactory(
    private val repository: KegiatanRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KegiatanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KegiatanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
