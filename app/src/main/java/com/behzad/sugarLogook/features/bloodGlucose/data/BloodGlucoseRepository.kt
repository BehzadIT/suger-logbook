package com.behzad.sugarLogook.features.bloodGlucose.data

import kotlinx.coroutines.flow.Flow

class BloodGlucoseRepository(private val bloodGlucoseDao: BloodGlucoseDao) {

    suspend fun addNewEntry(entry: BloodGlucoseEntry) {
        bloodGlucoseDao.insert(entry)
    }

    fun getAllEntries(): Flow<List<BloodGlucoseEntry>> {
        return bloodGlucoseDao.getAll()
    }
}




