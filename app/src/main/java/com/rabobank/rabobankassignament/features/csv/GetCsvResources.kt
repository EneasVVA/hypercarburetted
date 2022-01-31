package com.rabobank.rabobankassignament.features.csv

import com.rabobank.rabobankassignament.exception.Failure
import com.rabobank.rabobankassignament.functional.Either
import com.rabobank.rabobankassignament.functional.map
import com.rabobank.rabobankassignament.interactor.BaseUseCase
import com.rabobank.rabobankassignament.interactor.NoArguments
import javax.inject.Inject

class GetCsvResources
@Inject constructor(private val csvRepository: CsvRepository) : BaseUseCase<List<CsvResource>, NoArguments>() {

    override suspend fun run(arguments: NoArguments) : Either<Failure, List<CsvResource>> {
        val fileExtension = ".csv"

        val result = csvRepository.getResourcesList().map {
            it.filter { csvResource -> csvResource.filename.endsWith(fileExtension) }
        }

        return result
    }
}