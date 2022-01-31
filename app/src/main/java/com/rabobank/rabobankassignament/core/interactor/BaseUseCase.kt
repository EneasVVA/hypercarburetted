package com.rabobank.rabobankassignament.interactor

import com.rabobank.rabobankassignament.exception.Failure
import com.rabobank.rabobankassignament.functional.Either
import kotlinx.coroutines.*

typealias NoArguments = Any

abstract class BaseUseCase<out ReturnType, in Arguments> where ReturnType : Any {

    abstract suspend fun run(arguments: Arguments): Either<Failure, ReturnType>

    operator fun invoke(
        arguments: Arguments,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, ReturnType>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                run(arguments)
            }
            onResult(deferred.await())
        }
    }
}