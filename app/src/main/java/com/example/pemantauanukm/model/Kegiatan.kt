package com.example.pemantauanukm.model

data class Kegiatan(
    val id: Int = 0,
    val nama: String,         // Teks
    val tanggal: String,      // Teks
    val deskripsi: String,    // Teks
    val anggaran: Double,     // Angka
    val fotoUrl: String       // File/URL
)
