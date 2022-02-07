package com.rabobank.rabobankassignament.core.platform

import androidx.lifecycle.MutableLiveData
import com.rabobank.rabobankassignament.AndroidTest
import com.rabobank.rabobankassignament.core.exception.Failure
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(Failure.CsvResourcesCannotBeListedFailure)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.CsvResourcesCannotBeListedFailure::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}