package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.Either
import com.rabobank.rabobankassignment.core.functional.map
import com.rabobank.rabobankassignment.core.interactor.BaseUseCase
import javax.inject.Inject

class GetCsvResources
@Inject constructor(private val csvRepository: CsvRepository) :
    BaseUseCase<List<CsvResource>, BaseUseCase.NoArguments>() {

    override suspend fun buildUseCase(arguments: NoArguments): Either<Failure, List<CsvResource>> =
        csvRepository.getResourcesList().map {
            it.filter { csvResource -> csvResource.filename.endsWith(FILE_EXTENSION) }
        }

    companion object {
        private const val FILE_EXTENSION = ".csv"
    }
}