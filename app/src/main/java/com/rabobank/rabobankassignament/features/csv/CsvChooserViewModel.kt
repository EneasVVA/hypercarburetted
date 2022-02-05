package com.rabobank.rabobankassignament.features.csv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rabobank.rabobankassignament.core.platform.BaseViewModel
import com.rabobank.rabobankassignament.interactor.BaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CsvChooserViewModel
@Inject constructor(private val getCsvResources: GetCsvResources) : BaseViewModel() {

    private val _resources: MutableLiveData<List<CsvResourceView>> = MutableLiveData()
    val resources: LiveData<List<CsvResourceView>> = _resources

    fun loadResources() =
        getCsvResources.execute(BaseUseCase.NoArguments(), viewModelScope) {
            it.fold(::handleFailure, ::handleCsvResourcesList)
        }

    private fun handleCsvResourcesList(csvResources: List<CsvResource>) {
        _resources.value = csvResources.map { CsvResourceView(it.resource, it.filename) }
    }

    fun setResourceSelection(csvResourceView: CsvResourceView) {
        _resources.value = resources.value?.map {
            it.copy(selected = csvResourceView == it)
        }
    }

    fun getResourceSelected() = resources.value?.firstOrNull { it.selected }

}