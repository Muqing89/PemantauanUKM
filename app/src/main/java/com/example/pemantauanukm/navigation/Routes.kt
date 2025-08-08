package com.example.pemantauanukm.navigation

// Routes yang kamu tulis ini adalah tempat penyimpanan
// semua rute/URL internal untuk navigasi antar screen
// di aplikasi Jetpack Compose.

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val DASHBOARD_ORMAWA = "dashboard"
    const val KEGIATAN_LIST = "list_kegiatan"
    const val TAMBAH_KEGIATAN = "tambah_kegiatan"
    const val EDIT_KEGIATAN = "edit_kegiatan/{id}"
    const val DETAIL_KEGIATAN = "detail_kegiatan/{id}"

    fun editKegiatan(id: Int): String = "edit_kegiatan/$id"
    fun detailKegiatan(id: Int): String = "detail_kegiatan/$id"
}
