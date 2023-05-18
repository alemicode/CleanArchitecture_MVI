package com.codingwithmitch.cleannotes.business.domain.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateUtil
@Inject
constructor(
    private val dateFormat: SimpleDateFormat
) {
    // Date format: "2019-07-23 HH:mm:ss"

    fun removeTimeFromDateString(sd: String): String {
        return sd.substring(0, sd.indexOf(" "))
    }

    fun convertFirebaseTimestampToStringData(): String {
        return "2020/01/01"
    }


    fun getCurrentTimestamp(): String {
        return dateFormat.format(Date())
    }

}