package com.rabobank.rabobankassignment.core.interactor

import com.rabobank.rabobankassignment.AndroidTest
import com.rabobank.rabobankassignment.UnitTest
import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.Either
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

class BaseUseCaseTest : UnitTest() {

    private val useCase = MyUseCase()

    @Test
    fun `running use case should return 'Either' of use case type`() {
        val params = MyArguments(TYPE_PARAM)
        val result = runBlocking { useCase.buildUseCase(params) }

        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    data class MyType(val name: String)
    data class MyArguments(val name: String) : BaseUseCase.Arguments

    private inner class MyUseCase : BaseUseCase<MyType, MyArguments>() {
        override suspend fun buildUseCase(arguments: MyArguments): Either<Failure, MyType> =
            Either.Right(MyType(TYPE_TEST))
    }

    companion object {
        private const val TYPE_TEST = "Test"
        private const val TYPE_PARAM = "ParamTest"
    }
}