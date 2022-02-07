package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.AndroidTest
import com.rabobank.rabobankassignment.core.functional.Either
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.junit.Before
import org.junit.Test

class CsvChooserViewModelTest : AndroidTest() {

    private lateinit var csvChooserViewModel : CsvChooserViewModel

    @MockK private lateinit var getCsvResources: GetCsvResources

    @Before
    fun setUp() {
        csvChooserViewModel = CsvChooserViewModel(getCsvResources)
    }

    @Test fun `loading csv should update live data`() {
        val csvResource = CsvResource(1001, "filename")
        coEvery { getCsvResources.buildUseCase(any()) } returns Either.Right(listOf(csvResource))

        csvChooserViewModel.resources.observeForever {
            it.size `should be equal to` 1
            it[0] `should be equal to` csvResource
        }

        runBlocking {
            csvChooserViewModel.loadResources()
        }
    }


    @Test
    fun `should set resource selection`() {
        val csvResource = CsvResource(1001, "filename")
        val csvResourceView = CsvResourceView(csvResource.resource, csvResource.filename)

        coEvery { getCsvResources.buildUseCase(any()) } returns Either.Right(listOf(csvResource))

        csvChooserViewModel.resources.observeForever {
            it[0].selected `should be` true
        }

        runBlocking {
            csvChooserViewModel.loadResources()
            csvChooserViewModel.setResourceSelection(csvResourceView)
        }
    }
}