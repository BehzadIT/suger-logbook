package com.behzad.sugarLogbook.features.bloodGlucose.data.units.usecase

import com.behzad.sugarLogbook.features.bloodGlucose.data.units.BloodGlucoseUnitRepository
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.GlucoseUnit

class UpdateBloodGlucoseUnitUseCase(private val bloodGlucoseUnitRepository: BloodGlucoseUnitRepository) {
    suspend operator fun invoke(unit: GlucoseUnit){
        return bloodGlucoseUnitRepository.setUnit(unit)
    }
}