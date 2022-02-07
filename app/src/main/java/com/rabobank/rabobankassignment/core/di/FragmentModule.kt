package com.rabobank.rabobankassignment.core.di

import com.rabobank.rabobankassignment.features.csv.CsvLineView
import com.rabobank.rabobankassignment.features.csv.CsvSheetAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideCsvSheetAdapter() = CsvSheetAdapter(
        diffCallback = CsvLineView.diffCallback,
        mainDispatcher = Dispatchers.Main,
        workerDispatcher = Dispatchers.Default
    )
}