package com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.units.BloodGlucoseUnitRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.units.GlucoseUnit
import kotlinx.coroutines.flow.Flow

class GetBloodGlucoseUnitUseCase(private val bloodGlucoseUnitRepository: BloodGlucoseUnitRepository) {
    operator fun invoke(): Flow<GlucoseUnit> {
        return bloodGlucoseUnitRepository.getUnit()
    }
}