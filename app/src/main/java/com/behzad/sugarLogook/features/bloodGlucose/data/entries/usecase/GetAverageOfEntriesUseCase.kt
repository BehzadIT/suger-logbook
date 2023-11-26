package com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.ConvertGlucoseUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.units.BloodGlucoseUnitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetAverageOfEntriesUseCase(
    private val bloodGlucoseRepository: BloodGlucoseRepository,
    private val unitRepository: BloodGlucoseUnitRepository,
    private val convertGlucoseUnitUseCase: ConvertGlucoseUnitUseCase
) {
    operator fun invoke(): Flow<Double> {
        return combine(
            bloodGlucoseRepository.getAllEntries(),
            unitRepository.getUnit(),
        ) { entries, currentUnit ->
            val sumOfConvertedLevels = entries.filter { it.unit != currentUnit }
                .sumOf { convertGlucoseUnitUseCase(it.level, currentUnit).toDouble() }
            val sumOfOriginalLevels =
                entries.filter { it.unit == currentUnit }.sumOf { it.level.toDouble() }
            sumOfConvertedLevels + sumOfOriginalLevels / entries.size
        }
    }
}