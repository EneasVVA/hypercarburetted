package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.UnitTest
import com.rabobank.rabobankassignment.core.functional.Either
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ReadCsvResourceTest : UnitTest() {
    private lateinit var readCsvResource : ReadCsvResource

    @MockK lateinit var csvReaderInputStreamDataSource : CsvReaderInputStreamDataSource<CsvLine>
    @MockK private lateinit var csvRepository: CsvRepository

    @Before
    fun setUp() {
        readCsvResource = ReadCsvResource(csvRepository)
        every { csvRepository.readCsvResource(
            RESOURCE_ID,
            ReadCsvResource.DEFAULT_DELIMITER,
            ReadCsvResource.DEFAULT_QUOTE)
        } returns Either.Right(csvReaderInputStreamDataSource)
    }

    @Test
    fun `should get csv resource data from repository`() {
        runBlocking { readCsvResource.execute(ReadCsvResource.ArgumentsImpl(RESOURCE_ID)) }

        verify(exactly = 1) { csvRepository.readCsvResource(RESOURCE_ID, ReadCsvResource.DEFAULT_DELIMITER, ReadCsvResource.DEFAULT_QUOTE) }
    }

    companion object {
        private const val RESOURCE_ID = 0
    }
}