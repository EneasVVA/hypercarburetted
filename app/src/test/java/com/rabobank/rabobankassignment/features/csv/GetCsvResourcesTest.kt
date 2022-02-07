package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.UnitTest
import com.rabobank.rabobankassignment.core.functional.Either
import com.rabobank.rabobankassignment.core.interactor.BaseUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCsvResourcesTest : UnitTest() {
    private lateinit var getCsvResources: GetCsvResources

    @MockK
    private lateinit var csvRepository: CsvRepository

    @Before
    fun setUp() {
        getCsvResources = GetCsvResources(csvRepository)
        every { csvRepository.getResourcesList() } returns Either.Right(emptyList())
    }

    @Test
    fun `should get csv resources list from repository`() {
        runBlocking { getCsvResources.execute(BaseUseCase.NoArguments()) }

        verify(exactly = 1) { csvRepository.getResourcesList() }
    }
}