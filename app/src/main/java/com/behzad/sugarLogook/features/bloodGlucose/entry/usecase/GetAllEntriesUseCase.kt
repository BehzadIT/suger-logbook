package com.behzad.sugarLogook.features.bloodGlucose.entry.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow

class GetAllEntriesUseCase(private val bloodGlucoseRepository: BloodGlucoseRepository) {
    operator fun invoke(): Flow<List<BloodGlucoseEntry>> {
        return bloodGlucoseRepository.getAllEntries()
    }
}