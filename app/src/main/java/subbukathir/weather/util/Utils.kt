package subbukathir.weather.util

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun roundOf(temp: String): String {
        val doubleTemp = temp.toDouble()
        return Math.round(doubleTemp).toString()
    }

    fun unixToDate(timeStamp: Long): String? {
        val today: Date = Calendar.getInstance().time
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow: Date = calendar.time

        val time = Date(timeStamp as Long * 1000)

        val sdf = SimpleDateFormat("dd MMM yyyy")

        if (sdf.format(today).equals(sdf.format(time))) return "Today"
        else if (sdf.format(tomorrow).equals(sdf.format(time))) return "Tomorrow"
        return sdf.format(time)
    }
}