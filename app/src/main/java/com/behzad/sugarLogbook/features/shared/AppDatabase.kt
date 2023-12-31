package com.behzad.sugarLogbook.features.shared

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseDao
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.InstantConverter

@Database(
    entities = [BloodGlucoseEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(InstantConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bloodGlucoseDao(): BloodGlucoseDao

    companion object {
        private const val DATABASE_NAME = "sugar-logbook-database"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}