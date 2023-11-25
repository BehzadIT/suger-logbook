package com.behzad.sugarLogook.features.bloodGlucose.entry.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseRepository

class AddNewEntryUseCase(private val bloodGlucoseRepository: BloodGlucoseRepository) {
    suspend operator fun invoke(entry: BloodGlucoseEntry){
        return bloodGlucoseRepository.addNewEntry(entry)
    }
}