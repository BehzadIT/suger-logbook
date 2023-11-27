package com.behzad.sugarLogbook.features.bloodGlucose.data.units.usecase

import com.behzad.sugarLogbook.features.bloodGlucose.data.units.BloodGlucoseUnitRepository
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.GlucoseUnit
import kotlinx.coroutines.flow.Flow

class GetBloodGlucoseUnitUseCase(private val bloodGlucoseUnitRepository: BloodGlucoseUnitRepository) {
    operator fun invoke(): Flow<GlucoseUnit> {
        return bloodGlucoseUnitRepository.getUnit()
    }
}