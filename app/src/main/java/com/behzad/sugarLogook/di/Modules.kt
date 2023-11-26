package com.behzad.sugarLogook.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.behzad.sugarLogook.features.bloodGlucose.UserSearchViewModel
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase.AddNewEntryUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase.GetAllEntriesInCurrentUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.usecase.GetAverageOfEntriesUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.units.BloodGlucoseUnitDataSource
import com.behzad.sugarLogook.features.bloodGlucose.data.units.BloodGlucoseUnitRepository
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.ConvertGlucoseUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.GetBloodGlucoseUnitUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.units.usecase.UpdateBloodGlucoseUnitUseCase
import com.behzad.sugarLogook.features.shared.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Modules {
    val app = module {
        single { BloodGlucoseRepository(get()) }
        single { BloodGlucoseUnitRepository(get()) }
        single { BloodGlucoseUnitDataSource(get()) }
    }
    val useCases = module {
        factory { GetBloodGlucoseUnitUseCase(get()) }
        factory { UpdateBloodGlucoseUnitUseCase(get()) }
        factory { GetAverageOfEntriesUseCase(get(), get(), get()) }
        factory { ConvertGlucoseUnitUseCase() }
        factory { GetAllEntriesInCurrentUnitUseCase(get(), get(), get()) }
        factory { AddNewEntryUseCase(get()) }
    }
    val viewModels = module {
        viewModel { UserSearchViewModel(androidApplication(), get(), get(), get(), get(), get()) }
    }
    val database = module {
        single { AppDatabase.getInstance(androidContext()) }
        single {
            val database = get<AppDatabase>()
            database.bloodGlucoseDao()
        }
    }
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sugar.logbook")

    val dataStore = module {
        single {
            androidContext().dataStore
        }
    }
}



