package com.rabobank.rabobankassignament.core.di

import android.content.Context
import com.rabobank.rabobankassignament.core.platform.AndroidService
import com.rabobank.rabobankassignament.features.csv.CsvLineView
import com.rabobank.rabobankassignament.features.csv.CsvRepository
import com.rabobank.rabobankassignament.features.csv.CsvSheetAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAndroidService(@ApplicationContext context: Context): AndroidService =
        AndroidService(context)

    @Provides
    @Singleton
    fun provideCsvRepository(dataSource: CsvRepository.Local): CsvRepository = dataSource

    @Provides
    @Singleton
    fun provideCsvSheetAdapter(): CsvSheetAdapter {
        return CsvSheetAdapter(CsvLineView.diffCallback)
    }
}