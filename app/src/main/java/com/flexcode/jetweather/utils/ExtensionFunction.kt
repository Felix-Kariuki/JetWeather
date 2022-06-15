package com.flexcode.jetweather.utils


import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun formatTime(hourlyDate: String): String {
    val deviceFormat = SimpleDateFormat("hh:mma")
    deviceFormat.timeZone = TimeZone.getDefault()
    val result1: Date = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(hourlyDate) as Date
    return deviceFormat.format(result1)
}

fun dayOfTheWeek(mDate: String): String {
    val locale = Locale.getDefault()
    val date = SimpleDateFormat("yyyy-MM-dd").parse(mDate)
    val weekdayNameFormat: DateFormat = SimpleDateFormat("EEE", locale)
    return weekdayNameFormat.format(date as Date)
}