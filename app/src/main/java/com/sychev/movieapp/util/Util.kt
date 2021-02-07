package com.sychev.movieapp.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun getMonthByNumber(i: Int): String? {
    return when (i) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> null
    }
}

fun getAgeByBirthDay(day: Int, month: Int, year: Int): Int {
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    val currentYear = date.substring(0 until date.indexOf('-'))
    val currentMonth = date.substring(currentYear.length + 1 until currentYear.length + 3)
    val currentDay = date.substring((currentYear.length + currentMonth.length + 2)..date.lastIndex)

    return if (currentMonth.toInt() <= month && currentDay.toInt() < day) currentYear.toInt() - year - 1 else currentYear.toInt() - year
}