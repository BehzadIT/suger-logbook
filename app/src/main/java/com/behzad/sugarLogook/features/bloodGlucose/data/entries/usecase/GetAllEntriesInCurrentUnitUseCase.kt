package com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.ConvertGlucoseUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.GetBloodGlucoseUnitUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetAllEntriesInCurrentUnitUseCase(
    private val bloodGlucoseRepository: BloodGlucoseRepository,
    private val getBloodGlucoseUnitUseCase: GetBloodGlucoseUnitUseCase,
    private val convertGlucoseUnitUseCase: ConvertGlucoseUnitUseCase
) {
    operator fun invoke(): Flow<List<BloodGlucoseEntry>> {
        return combine(
            bloodGlucoseRepository.getAllEntries(), getBloodGlucoseUnitUseCase()
        ) { entries, currentUnit ->
            entries.map { entry ->
                if (entry.unit == currentUnit) {
                    entry
                } else {
                    entry.copy(
                        level = convertGlucoseUnitUseCase(
                            currentLevel = entry.level, targetUnit = currentUnit
                        ), unit = currentUnit
                    )

                }
            }
        }
    }
}