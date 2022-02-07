package com.rabobank.rabobankassignment.features.csv

import androidx.annotation.RawRes
import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.Either
import com.rabobank.rabobankassignment.core.interactor.BaseUseCase
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