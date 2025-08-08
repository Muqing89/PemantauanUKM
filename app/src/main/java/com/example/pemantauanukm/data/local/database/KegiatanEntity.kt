package com.example.pemantauanukm.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//File ini adalah tabel di database.
// AppDatabase akan mendaftarkan KegiatanEntity di bagian
// @Database(entities = [...]).

@Entity(tableName = "kegiatan")
data class KegiatanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,            // Teks
    val tanggal: String,         // Teks
    val deskripsi: String,       // Teks
    val anggaran: Double,        // Angka
    val fotoUrl: String          // File atau URL (misal: link ke gambar)
)
