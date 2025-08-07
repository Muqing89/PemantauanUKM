package com.example.pemantauanukm.navigation

object Routes {
    const val LOGIN = "login"
    const val DASHBOARD_ORMAWA = "dashboard"
    const val DASHBOARD_ADMIN = "dashboard_admin"
    const val KEGIATAN_LIST = "list_kegiatan"
    const val TAMBAH_KEGIATAN = "tambah_kegiatan"
    const val EDIT_KEGIATAN = "edit_kegiatan/{id}"
    const val DETAIL_KEGIATAN = "detail_kegiatan/{id}"

    fun editKegiatan(id: Int): String = "edit_kegiatan/$id"
    fun detailKegiatan(id: Int): String = "detail_kegiatan/$id"
}
