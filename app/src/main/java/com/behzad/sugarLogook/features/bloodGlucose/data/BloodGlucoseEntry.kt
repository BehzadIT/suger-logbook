package com.behzad.sugarLogook.features.bloodGlucose.data

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.behzad.sugarLogook.R
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

enum class GlucoseUnit(@Ignore @StringRes val textFormatRes: Int) {
    MgperdL(R.string.unit_mg_per_dl),
    MmolPerL(R.string.unit_mmol_per_L)
}

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