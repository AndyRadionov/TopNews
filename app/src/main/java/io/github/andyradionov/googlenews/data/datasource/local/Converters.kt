package io.github.andyradionov.googlenews.data.datasource.local

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author Andrey Radionov
 */
class Converters {
    @TypeConverter
    fun dateToLong(date: Date) = date.time

    @TypeConverter
    fun longToDate(longTime: Long) = Date(longTime)
}