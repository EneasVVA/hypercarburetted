package com.rabobank.rabobankassignament.features.csv

import com.rabobank.rabobankassignament.core.platform.AndroidService
import com.rabobank.rabobankassignament.core.platform.BaseRepository
import com.rabobank.rabobankassignament.core.exception.Failure
import com.rabobank.rabobankassignament.core.functional.Either
import com.rabobank.rabobankassignament.core.functional.map
import javax.inject.Inject
import kotlin.Int

interface CsvRepository : BaseRepository {
    fun getResourcesList() : Either<Failure, List<CsvResource>>
    fun readCsvResource(csvResource: Int): Either<Failure, CsvReaderLocalDataSource<CsvLine>>

    class Local
    @Inject constructor(private val androidService: AndroidService): BaseRepository.Local, CsvRepository {
        override fun getResourcesList(): Either<Failure, List<CsvResource>> =
            androidService.getResourcesList().map { list ->
                list.map {
                        csvResourceDto -> csvResourceDto.toCsvResource()
                }
            }

        override fun readCsvResource(csvResource: Int): Either<Failure, CsvReaderLocalDataSource<CsvLine>> {
            val csvDataSource = CsvReaderLocalDataSource(
                inputStreamOpener = { androidService.context.resources.openRawResource(csvResource) },
                transform = { csvLineDto -> csvLineDto.toCsvLine(",")
            })

            return Either.Right(csvDataSource)
        }
    }
}