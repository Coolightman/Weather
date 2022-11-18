package by.coolightman.weather.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

fun Long.toMonthDay(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toMonthDay
    }
    val month = getMonthName(calendar)
    val day = getDayOfMonth(calendar)
    return "$month $day"
}

@SuppressLint("SimpleDateFormat")
fun Long.toDayOfWeek(): String {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toDayOfWeek
    }

    val format = SimpleDateFormat("EEEE")
    return format.format(calendar.time)
}

private fun getDayOfMonth(calendar: Calendar): Int = calendar.get(Calendar.DAY_OF_MONTH)


@SuppressLint("SimpleDateFormat")
private fun getMonthName(calendar: Calendar): String {
    val format = SimpleDateFormat("MMMM")
    return format.format(calendar.time)
}
