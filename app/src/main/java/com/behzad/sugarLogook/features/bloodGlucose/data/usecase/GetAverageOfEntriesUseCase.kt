package com.behzad.sugarLogook.features.bloodGlucose.data.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseRepository
import kotlinx.coroutines.flow.Flow

class GetAverageOfEntriesUseCase(private val bloodGlucoseRepository: BloodGlucoseRepository) {
    operator fun invoke(): Flow<List<BloodGlucoseEntry>> {
        return bloodGlucoseRepository.getAllEntries()
    }
}