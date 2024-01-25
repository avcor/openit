package com.example.openit.utils

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.openit.R
import com.example.openit.activities.home.model.LinkData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

val TAG = "abcd";

fun makeDateSetLineGraph(yAxis: ArrayList<Int>, context:Context): LineData {
    val entry = ArrayList<Entry>();
    for(i in 0 .. yAxis.size-1 ){
        entry.add(Entry(i.toFloat(), yAxis[i].toFloat()))
    }

    val dataSet = LineDataSet(entry, "").apply {
        color = Color.BLUE
        lineWidth = 2f
        valueTextSize = 10f
        valueTextColor = Color.BLACK
        valueTextSize = 0f
        setDrawCircles(false)
        setDrawFilled(true)
        fillDrawable = ContextCompat.getDrawable(context, R.drawable.gradeint)
    }
    return LineData(dataSet)
}

fun getGreetings(): String {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

    val greeting = when (hourOfDay) {
        in 4..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }

    return "$greeting"
}


@TargetApi(Build.VERSION_CODES.O)
fun getXYaxis(linkData: LinkData): LinkData {
    val current = LocalDateTime.now()
    val xAxis = ArrayList<String>()
    val yAxis = ArrayList<Int>();
    val xTick = ArrayList<String>();

    for(i in 30 downTo  0){
        val r = current.minusDays(i.toLong())
        val localDateString = r.toLocalDate().toString();
        yAxis.add( linkData.data.overall_url_chart?.get(r.toLocalDate().toString()) ?: 0 )
        xAxis.add(localDateString)

        xTick.add(r.toLocalDate().dayOfMonth.toString() + '/' + r.toLocalDate().monthValue.toString());
    }

    linkData.xAxis = xAxis;
    linkData.yAxis = yAxis;
    linkData.xTick = xTick;

    return linkData
}

@TargetApi(Build.VERSION_CODES.O)
fun getRangeDateNow_30D_back(): String {
    val today = LocalDate.now()
    val thirtyDaysAgo = today.minusDays(30)

    val formatter = DateTimeFormatter.ofPattern("dd MMM")
    val formattedToday = today.format(formatter)
    val formattedThirtyDaysAgo = thirtyDaysAgo.format(formatter)

    return "${formattedThirtyDaysAgo} - ${formattedToday}"
}