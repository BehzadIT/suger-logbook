package com.behzad.sugarLogbook.features.bloodGlucose.data.entries

import kotlinx.coroutines.flow.Flow

class BloodGlucoseRepository(private val bloodGlucoseDao: BloodGlucoseDao) {

    suspend fun addNewEntry(entry: BloodGlucoseEntry) {
        bloodGlucoseDao.insert(entry)
    }

    fun getAllEntries(): Flow<List<BloodGlucoseEntry>> {
        return bloodGlucoseDao.getAll()
    }
}




