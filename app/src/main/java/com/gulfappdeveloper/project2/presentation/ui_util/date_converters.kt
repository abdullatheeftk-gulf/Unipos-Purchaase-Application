package com.gulfappdeveloper.project2.presentation.ui_util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.localDateToStringConverter():String{
    return format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}