package com.rabobank.rabobankassignment.features.csv

import androidx.annotation.RawRes
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.rabobank.rabobankassignment.core.exception.Failure
import com.rabobank.rabobankassignment.core.functional.getOrElse
import com.rabobank.rabobankassignment.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CsvParsedViewModel
@Inject constructor(private val readCsvResource: ReadCsvResource) : BaseViewModel() {
    fun loadCsv(@RawRes csvResource: Int): Flow<PagingData<CsvLineView>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 10,
                pageSize = 10
            ),
            0,
        ) {
            readCsvResource.execute(ReadCsvResource.ArgumentsImpl(csvResource), viewModelScope)
                .getOrElse(null)
                ?: throw RuntimeException("CSV Cannot be loaded")
        }.flow
            .map {
                it.map { csvLine: CsvLine ->
                    CsvLineView(
                        csvLine.position.toString(),
                        csvLine.columns
                    )
                }
            }
            .cachedIn(viewModelScope)
            .catch { handleFailure(Failure.CsvGetDataError) }
    }
}