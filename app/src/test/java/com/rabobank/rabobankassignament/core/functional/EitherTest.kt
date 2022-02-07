package com.rabobank.rabobankassignament.core.functional

import org.junit.Test
import com.rabobank.rabobankassignament.UnitTest
import com.rabobank.rabobankassignament.core.functional.Either.Left
import com.rabobank.rabobankassignament.core.functional.Either.Right
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo

class EitherTest : UnitTest() {

    @Test
    fun `Either Right should return correct type`() {
        val result = Right("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.fold({},
            { right ->
                right shouldBeInstanceOf String::class.java
                right shouldEqualTo "ironman"
            })
    }

    @Test
    fun `Either Left should return correct type`() {
        val result = Left("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isLeft shouldBe true
        result.isRight shouldBe false
        result.fold(
            { left ->
                left shouldBeInstanceOf String::class.java
                left shouldEqualTo "ironman"
            }, {})
    }

    @Test
    fun `Either fold should ignore passed argument if it is Right type`() {
        val success = "Success"
        val result = Right(success).getOrElse("Other")

        result shouldEqualTo success
    }

    @Test
    fun `Either fold should return argument if it is Left type`() {
        val other = "Other"
        val result = Left("Failure").getOrElse(other)

        result shouldEqual other
    }

    @Test
    fun `given fold is called, when either is Right, applies fnR and returns its result`() {
        val either = Right("Success")
        val result = either.fold({ fail("Shouldn't be executed") }) { 5 }

        result shouldBe 5
        either.isRight shouldBe true
    }

    @Test
    fun `given fold is called, when either is Left, applies fnL and returns its result`() {
        val either = Left(12)

        val foldResult = "Fold Result"
        val result = either.fold({ foldResult }) { fail("Shouldn't be executed") }

        result shouldBe foldResult
        either.isLeft shouldBe true
    }

    @Test
    fun `given flatMap is called, when either is Left, doesn't invoke function and returns original Either`() {
        val either = Left(12)

        val result = either.flatMap { Right(20) }

        result.isLeft shouldBe true
        result shouldEqual either
    }

    @Test
    fun `given map is called, when either is Right, invokes function with right value and returns a new Either`() {
        val success = "Success"
        val resultValue = "Result"
        val either = Right(success)

        val result = either.map {
            it shouldBe success
            resultValue
        }

        result shouldEqual Right(resultValue)
    }

    @Test
    fun `given map is called, when either is Left, doesn't invoke function and returns original Either`() {
        val either = Left(12)

        val result = either.map { Either.Right(20) }

        result.isLeft shouldBe true
        result shouldEqual either
    }
}
