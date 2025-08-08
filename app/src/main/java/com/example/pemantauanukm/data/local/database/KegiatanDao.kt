package com.example.pemantauanukm.data.local.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// interface berisi query untuk mengelola data (KegiatanDao)

@Dao
interface KegiatanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kegiatan: KegiatanEntity)

    @Update
    suspend fun update(kegiatan: KegiatanEntity)

    @Delete
    suspend fun delete(kegiatan: KegiatanEntity)

    @Query("SELECT * FROM kegiatan ORDER BY tanggal DESC")
    fun getAllKegiatan(): Flow<List<KegiatanEntity>>

    // Gunakan Flow supaya bisa collectAsState di Compose
    @Query("SELECT * FROM kegiatan WHERE id = :id")
    fun getKegiatanById(id: Int): Flow<KegiatanEntity?>



}
