package com.task.noteapp.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatDate(): String {
    return SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(this)
}