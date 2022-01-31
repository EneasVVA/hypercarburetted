package com.rabobank.rabobankassignament.features.csv

import com.rabobank.rabobankassignament.core.platform.AndroidService
import com.rabobank.rabobankassignament.core.platform.BaseRepository
import com.rabobank.rabobankassignament.exception.Failure
import com.rabobank.rabobankassignament.functional.Either
import javax.inject.Inject

interface CsvRepository : BaseRepository {
    fun getResourcesList() : Either<Failure, List<CsvResource>>

    class Local
    @Inject constructor(private val androidService: AndroidService): BaseRepository.Local, CsvRepository {
        override fun getResourcesList(): Either<Failure, List<CsvResource>> =
            androidService.getResourcesList()
    }
}