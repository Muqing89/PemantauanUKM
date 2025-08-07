package com.example.pemantauanukm.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [KegiatanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun kegiatanDao(): KegiatanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kegiatan_database"
                )
                    .fallbackToDestructiveMigration(false) // opsional saat dev, jangan di production
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
