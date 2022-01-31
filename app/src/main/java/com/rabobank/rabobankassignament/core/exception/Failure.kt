package com.rabobank.rabobankassignament.exception

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure {
    object CsvCannotBeListedFailure : Failure()
}