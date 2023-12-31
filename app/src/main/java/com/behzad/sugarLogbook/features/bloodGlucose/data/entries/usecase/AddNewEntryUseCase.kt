package com.behzad.sugarLogbook.features.bloodGlucose.data.entries.usecase

import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseRepository

class AddNewEntryUseCase(private val bloodGlucoseRepository: BloodGlucoseRepository) {
    suspend operator fun invoke(entry: BloodGlucoseEntry){
        return bloodGlucoseRepository.addNewEntry(entry)
    }
}