package com.example.openit

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String.truncate(length: Int): String =
    if (this.length > length) this.substring(0, length) else this


fun String.militaryToDD_MMM_YYY(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    try {
        val date: Date = inputFormat.parse(this)
        return outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun String.militaryToDD_MMM(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    try {
        val date: Date = inputFormat.parse(this)
        return outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}
