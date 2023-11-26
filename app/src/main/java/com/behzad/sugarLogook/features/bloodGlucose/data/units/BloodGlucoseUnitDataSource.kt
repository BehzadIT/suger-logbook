package com.behzad.sugarLogook.features.bloodGlucose.data.units

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BloodGlucoseUnitDataSource(private val dataStore: DataStore<Preferences>) {
    suspend fun setUnit(unit: GlucoseUnit) {
        dataStore.edit {
            it[KEY_UNIT] = unit.ordinal
        }
    }

    fun getUnit(): Flow<GlucoseUnit> {
        return dataStore.data.map { preferences ->
            preferences[KEY_UNIT]?.let { GlucoseUnit.values()[it] } ?: GlucoseUnit.DEFAULT
        }
    }

    companion object {
        private val KEY_UNIT = intPreferencesKey("KEY_UNIT")
    }
}




