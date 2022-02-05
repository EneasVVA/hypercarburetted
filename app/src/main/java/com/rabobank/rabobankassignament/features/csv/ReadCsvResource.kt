package com.rabobank.rabobankassignament.features.csv

import androidx.annotation.RawRes
import com.rabobank.rabobankassignament.core.functional.Either
import com.rabobank.rabobankassignament.core.exception.Failure
import com.rabobank.rabobankassignament.interactor.BaseUseCase
import javax.inject.Inject
import kotlin.Int

class ReadCsvResource
@Inject constructor(private val csvRepository: CsvRepository) : BaseUseCase<CsvReaderLocalDataSource<CsvLine>, ReadCsvResource.ArgumentsImpl>() {

    override suspend fun buildUseCase(arguments: ArgumentsImpl) : Either<Failure, CsvReaderLocalDataSource<CsvLine>> =
        csvRepository.readCsvResource(arguments.csvResource)

    data class ArgumentsImpl(@RawRes val csvResource : Int) : Arguments
}