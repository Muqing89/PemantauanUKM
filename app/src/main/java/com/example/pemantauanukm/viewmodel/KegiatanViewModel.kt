package com.example.pemantauanukm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pemantauanukm.data.local.database.KegiatanEntity
import com.example.pemantauanukm.data.local.database.KegiatanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KegiatanViewModel(private val repository: KegiatanRepository) : ViewModel() {

    // Menggunakan StateFlow untuk daftar kegiatan
    private val _kegiatanList = MutableStateFlow<List<KegiatanEntity>>(emptyList())
    val kegiatanList: StateFlow<List<KegiatanEntity>> = _kegiatanList.asStateFlow()

    init {
        loadKegiatan()
    }

    private fun loadKegiatan() {
        // Mengumpulkan flow dari repository dan update StateFlow
        viewModelScope.launch {
            repository.getAllKegiatan().collect { list ->
                _kegiatanList.value = list
            }
        }
    }

    // Insert data baru
    fun insertKegiatan(kegiatan: KegiatanEntity) {
        viewModelScope.launch {
            repository.insert(kegiatan)
        }
    }

    // Update data
    fun updateKegiatan(kegiatan: KegiatanEntity) {
        viewModelScope.launch {
            repository.update(kegiatan)
        }
    }

    // Delete data
    fun deleteKegiatan(kegiatan: KegiatanEntity) {
        viewModelScope.launch {
            repository.delete(kegiatan)
        }
    }

    // Ambil kegiatan by ID, hasilnya Flow<KegiatanEntity?>
    fun getKegiatanById(id: Int) = repository.getKegiatanById(id)
}
