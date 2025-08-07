package com.example.pemantauanukm

import android.app.Application
import com.example.pemantauanukm.data.local.database.AppDatabase

class MyApp : Application() {

    // Inisialisasi Room Database
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}