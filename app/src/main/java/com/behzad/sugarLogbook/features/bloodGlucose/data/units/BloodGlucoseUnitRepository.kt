package com.behzad.sugarLogbook.features.bloodGlucose.data.units

import kotlinx.coroutines.flow.Flow

class BloodGlucoseUnitRepository(private val bloodGlucoseUnitDataSource: BloodGlucoseUnitDataSource) {
    suspend fun setUnit(unit: GlucoseUnit) {
        bloodGlucoseUnitDataSource.setUnit(unit)
    }

    fun getUnit(): Flow<GlucoseUnit> {
        return bloodGlucoseUnitDataSource.getUnit()
    }
}




