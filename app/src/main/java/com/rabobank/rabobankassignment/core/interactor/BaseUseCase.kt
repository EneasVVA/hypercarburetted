package com.rabobank.rabobankassignment.core.interactor

import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.Either
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out ReturnType, in Arguments>
constructor(
    private val coroutineContext: CoroutineContext = Dispatchers.Main,
    private val posCoroutineContext: CoroutineContext = Dispatchers.IO
) where ReturnType : Any, Arguments : BaseUseCase.Arguments {

    abstract suspend fun buildUseCase(arguments: Arguments): Either<Failure, ReturnType>

    fun execute(
        arguments: Arguments,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, ReturnType>) -> Unit = {}
    ) {
        scope.launch(coroutineContext) {
            val deferred = async(posCoroutineContext) {
                buildUseCase(arguments)
            }
            onResult(deferred.await())
        }
    }

    fun execute(
        arguments: Arguments,
        scope: CoroutineScope = GlobalScope
    ) = runBlocking(scope.coroutineContext) {
        buildUseCase(arguments)
    }

    interface Arguments

    class NoArguments : Arguments
}