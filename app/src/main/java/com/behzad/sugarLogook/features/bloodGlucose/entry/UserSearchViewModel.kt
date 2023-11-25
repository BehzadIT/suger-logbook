package com.behzad.sugarLogook.features.bloodGlucose.entry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.GlucoseUnit
import com.behzad.sugarLogook.features.bloodGlucose.entry.usecase.AddNewEntryUseCase
import com.behzad.sugarLogook.features.bloodGlucose.entry.usecase.GetAllEntriesUseCase
import com.behzad.sugarLogook.features.shared.LoadableData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant


class UserSearchViewModel(
    app: Application,
    private val addNewEntryUseCase: AddNewEntryUseCase,
    private val getAllEntriesUseCase: GetAllEntriesUseCase
) : AndroidViewModel(app) {
    private val _entries = getAllEntriesUseCase().map { LoadableData.Loaded(it) }
    val entries = _entries.stateIn(viewModelScope, SharingStarted.Lazily, LoadableData.NotLoaded)
    private val _unit = MutableStateFlow(GlucoseUnit.MgperdL)
    val unit = _unit.stateIn(viewModelScope, SharingStarted.Lazily, GlucoseUnit.MgperdL)
    private val _level = MutableStateFlow<Float?>(null)
    val level = _level.stateIn(viewModelScope, SharingStarted.Lazily, 0f)

    fun addNewEntry() {
        viewModelScope.launch {
            val entry = BloodGlucoseEntry(
                level = requireNotNull(level.value), unit = unit.value, createdTime = Instant.now()
            )
            addNewEntryUseCase(entry)
        }
    }

    fun setUnit(glucoseUnit: GlucoseUnit) {
        _unit.value = glucoseUnit
    }

    fun setLevel(level: Float?) {
        if (level == null) return
        _level.value = level
    }
}

internal fun String.isValidSearchQuery() = length >= 2
