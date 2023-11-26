package com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow

class GetAllEntriesUseCase(private val bloodGlucoseRepository: BloodGlucoseRepository) {
    operator fun invoke(): Flow<List<BloodGlucoseEntry>> {
        return bloodGlucoseRepository.getAllEntries()
    }
}