package com.example.pemantauanukm.data.local.database

import kotlin.text.insert

import kotlinx.coroutines.flow.Flow

class KegiatanRepository(private val dao: KegiatanDao) {

    fun getAllKegiatan(): Flow<List<KegiatanEntity>> = dao.getAllKegiatan()

    suspend fun insert(kegiatan: KegiatanEntity) {
        dao.insert(kegiatan)
    }

    suspend fun update(kegiatan: KegiatanEntity) {
        dao.update(kegiatan)
    }

    suspend fun delete(kegiatan: KegiatanEntity) {
        dao.delete(kegiatan)
    }

    fun getKegiatanById(id: Int): Flow<KegiatanEntity?> = dao.getKegiatanById(id)
}
