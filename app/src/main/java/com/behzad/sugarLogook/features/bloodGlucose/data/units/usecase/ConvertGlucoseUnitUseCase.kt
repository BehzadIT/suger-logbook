package com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase

import com.behzad.sugarLogook.features.bloodGlucose.data.units.GlucoseUnit

class ConvertGlucoseUnitUseCase() {
    operator fun invoke(
        currentLevel: Float, targetUnit: GlucoseUnit
    ): Float {
        return when (targetUnit) {
            GlucoseUnit.MgperdL -> currentLevel * 18.0182f
            GlucoseUnit.MmolPerL -> currentLevel / 18.0182f
        }
    }
}