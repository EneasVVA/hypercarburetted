package com.rabobank.rabobankassignament.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure {
    object CsvResourcesCannotBeListedFailure : Failure()
    object CsvGetDataError : Failure()
}