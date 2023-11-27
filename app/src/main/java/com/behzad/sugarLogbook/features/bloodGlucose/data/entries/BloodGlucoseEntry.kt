package com.behzad.sugarLogbook.features.bloodGlucose.data.entries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.GlucoseUnit
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale


@Entity
data class BloodGlucoseEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "level") val level: Float,
    @ColumnInfo(name = "unit") val unit: GlucoseUnit,
    @ColumnInfo(name = "createdTime") val createdTime: Instant
)

class InstantConverter {
    @TypeConverter
    fun fromTimestamp(epoch: Long?): Instant? {
        return epoch?.let { Instant.ofEpochSecond(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.epochSecond
    }
}

fun Instant.toReadableDate(): String? {
    val formatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())
    return formatter.format(this)
}