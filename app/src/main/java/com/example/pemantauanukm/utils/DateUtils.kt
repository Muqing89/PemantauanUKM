package com.example.pemantauanukm.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(date: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(Date(date))
    }

    fun currentTimestamp(): Long = System.currentTimeMillis()
}
