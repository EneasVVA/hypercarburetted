package com.rabobank.rabobankassignament.features.csv

import com.rabobank.rabobankassignament.AndroidTest
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.UnitTest
import com.rabobank.rabobankassignament.core.functional.Either
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.internal.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.stream.IntStream.range
import kotlin.streams.toList

class CsvParsedViewModelTest : AndroidTest() {

    private lateinit var csvParsedViewModel : CsvParsedViewModel

    @MockK
    private lateinit var readCsvResource: ReadCsvResource

    @Before
    fun setUp() {
        csvParsedViewModel = CsvParsedViewModel(readCsvResource)
        coEvery { readCsvResource.execute(any(), any()) } returns Either.Right(
            CsvReaderInputStreamDataSource( { csvSample.byteInputStream() }, { csvLineDto ->
                csvLineDto.toCsvLine(DELIMITER, QUOTA)
            })
        )
    }

    @Test
    fun `should adapter show stream csv parsed`() = runBlockingTest {
        val adapter = CsvSheetAdapter(diffCallback = CsvLineView.diffCallback, mainDispatcher = Dispatchers.Unconfined)
        val job = launch {
                csvParsedViewModel.loadCsv(0).collectLatest {
                    adapter.submitData(it)
                }
            }

        advanceUntilIdle()

        adapter.snapshot() `should be equal to` listOf(csvSampleLine)

        job.cancel()
    }

    companion object {
        private const val DELIMITER = ","
        private const val QUOTA = '"'
        val csvSampleLine = CsvLineView("0", columns = range(0, 4).toList().map { "Column $it" })
        val csvSample = csvSampleLine.columns!!.joinToString(DELIMITER) { QUOTA + it + QUOTA }
    }
}