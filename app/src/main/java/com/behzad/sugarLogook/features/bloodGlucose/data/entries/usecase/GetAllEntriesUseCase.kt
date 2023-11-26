package com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.ConvertGlucoseUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.GetBloodGlucoseUnitUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetAllEntriesUseCase(
    private val bloodGlucoseRepository: BloodGlucoseRepository,
    private val getBloodGlucoseUnitUseCase: GetBloodGlucoseUnitUseCase,
    private val convertGlucoseUnitUseCase: ConvertGlucoseUnitUseCase
) {
    operator fun invoke(): Flow<List<BloodGlucoseEntry>> {
        return combine(
            bloodGlucoseRepository.getAllEntries(), getBloodGlucoseUnitUseCase()
        ) { entries, currentUnit ->
            entries.map {
                if (it.unit == currentUnit) {
                    it
                } else {
                    it.copy(
                        level = convertGlucoseUnitUseCase(
                            currentLevel = it.level, targetUnit = currentUnit
                        ), unit = currentUnit
                    )

                }
            }
        }
    }
}