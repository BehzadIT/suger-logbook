package com.behzad.sugarLogook.di

import com.behzad.sugarLogook.features.bloodGlucose.data.BloodGlucoseRepository
import com.behzad.sugarLogook.features.bloodGlucose.UserSearchViewModel
import com.behzad.sugarLogook.features.bloodGlucose.data.usecase.AddNewEntryUseCase
import com.behzad.sugarLogook.features.bloodGlucose.data.usecase.GetAllEntriesUseCase
import com.behzad.sugarLogook.features.shared.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Modules {
    val app = module {
        single { BloodGlucoseRepository(get()) }
    }
    val useCases = module {
        factory { GetAllEntriesUseCase(get()) }
        factory { AddNewEntryUseCase(get()) }
    }
    val viewModels = module {
        viewModel { UserSearchViewModel(androidApplication(), get(), get()) }
    }
    val database = module {
        single { AppDatabase.getInstance(androidContext()) }
        single {
            val database = get<AppDatabase>()
            database.bloodGlucoseDao()
        }
    }

}



