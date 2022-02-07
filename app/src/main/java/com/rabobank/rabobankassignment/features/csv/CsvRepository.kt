package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.Either
import com.rabobank.rabobankassignment.core.functional.map
import com.rabobank.rabobankassignment.core.platform.AndroidService
import com.rabobank.rabobankassignment.core.platform.BaseRepository
import javax.inject.Inject

interface CsvRepository : BaseRepository {
    fun getResourcesList(): Either<Failure, List<CsvResource>>
    fun readCsvResource(
        csvResource: Int,
        delimiter: String,
        quote: Char
    ): Either<Failure, CsvReaderInputStreamDataSource<CsvLine>>

    class Local
    @Inject constructor(private val androidService: AndroidService) : BaseRepository.Local,
        CsvRepository {
        override fun getResourcesList(): Either<Failure, List<CsvResource>> =
            androidService.getResourcesList().map { list ->
                list.map { csvResourceDto ->
                    csvResourceDto.toCsvResource()
                }
            }

        override fun readCsvResource(
            csvResource: Int,
            delimiter: String,
            quote: Char
        ): Either<Failure, CsvReaderInputStreamDataSource<CsvLine>> {
            val csvDataSource = CsvReaderInputStreamDataSource(
                inputStreamOpener = { androidService.context.resources.openRawResource(csvResource) },
                transform = { csvLineDto ->
                    csvLineDto.toCsvLine(delimiter, quote)
                })

            return Either.Right(csvDataSource)
        }
    }
}