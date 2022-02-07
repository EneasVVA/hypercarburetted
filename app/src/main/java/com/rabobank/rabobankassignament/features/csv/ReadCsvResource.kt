package com.rabobank.rabobankassignament.features.csv

import androidx.annotation.RawRes
import com.rabobank.rabobankassignament.core.exception.Failure
import com.rabobank.rabobankassignament.core.functional.Either
import com.rabobank.rabobankassignament.core.interactor.BaseUseCase
import javax.inject.Inject

class ReadCsvResource
@Inject constructor(private val csvRepository: CsvRepository) :
    BaseUseCase<CsvReaderInputStreamDataSource<CsvLine>, ReadCsvResource.ArgumentsImpl>() {

    override suspend fun buildUseCase(arguments: ArgumentsImpl): Either<Failure, CsvReaderInputStreamDataSource<CsvLine>> =
        csvRepository.readCsvResource(arguments.csvResource, DEFAULT_DELIMITER, DEFAULT_QUOTE)

    data class ArgumentsImpl(@RawRes val csvResource: Int) : Arguments

    companion object {
        const val DEFAULT_DELIMITER = ","
        const val DEFAULT_QUOTE = '"'
    }
}