package com.behzad.sugarLogbook.features.bloodGlucose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.usecase.AddNewEntryUseCase
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.usecase.GetAllEntriesInCurrentUnitUseCase
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.usecase.GetAverageOfEntriesUseCase
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.GlucoseUnit
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.usecase.GetBloodGlucoseUnitUseCase
import com.behzad.sugarLogbook.features.bloodGlucose.data.units.usecase.UpdateBloodGlucoseUnitUseCase
import com.behzad.sugarLogbook.features.shared.LoadableData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant


class UserSearchViewModel(
    app: Application,
    private val addNewEntryUseCase: AddNewEntryUseCase,
    private val updateBloodGlucoseUnitUseCase: UpdateBloodGlucoseUnitUseCase,
    getAllEntriesInCurrentUnitUseCase: GetAllEntriesInCurrentUnitUseCase,
    getAverageOfEntriesUseCase: GetAverageOfEntriesUseCase,
    getBloodGlucoseUnitUseCase: GetBloodGlucoseUnitUseCase
) : AndroidViewModel(app) {

    private val _entriesResult = getAllEntriesInCurrentUnitUseCase().map { LoadableData.Loaded(it) }
    val entriesResult =
        _entriesResult.stateIn(viewModelScope, SharingStarted.Lazily, LoadableData.NotLoaded)

    private val _level = MutableStateFlow<Float?>(null)
    val level = _level.stateIn(viewModelScope, SharingStarted.Lazily, 0f)

    val average = getAverageOfEntriesUseCase().map { LoadableData.Loaded(it) }.stateIn(
        viewModelScope, SharingStarted.Lazily, LoadableData.NotLoaded
    )

    val unit = getBloodGlucoseUnitUseCase().stateIn(
        viewModelScope, SharingStarted.Lazily, GlucoseUnit.MgperdL
    )

    fun addNewEntry() {
        viewModelScope.launch {
            val entry = BloodGlucoseEntry(
                level = requireNotNull(level.value), unit = unit.value, createdTime = Instant.now()
            )
            addNewEntryUseCase(entry)
            _level.value = null
        }
    }

    fun setUnit(glucoseUnit: GlucoseUnit) {
        viewModelScope.launch {
            updateBloodGlucoseUnitUseCase(glucoseUnit)
        }
    }

    fun setLevel(level: Float?) {
        if (level == null) return
        _level.value = level
    }
}

internal fun String.isValidSearchQuery() = length >= 2
