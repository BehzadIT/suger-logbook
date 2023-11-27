package com.behzad.sugarLogbook.features.bloodGlucose.data.entries

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodGlucoseDao {
    @Query("SELECT * FROM BloodGlucoseEntry")
    fun getAll(): Flow<List<BloodGlucoseEntry>>

    @Query("SELECT * FROM BloodGlucoseEntry WHERE id = (:id)")
    fun get(id: Long): Flow<BloodGlucoseEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locomotives: BloodGlucoseEntry): Long
}