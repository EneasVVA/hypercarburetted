package com.rabobank.rabobankassignament.features.csv

import androidx.annotation.RawRes
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.rabobank.rabobankassignament.core.functional.getOrElse
import com.rabobank.rabobankassignament.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException
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
        )  {
            readCsvResource.execute(ReadCsvResource.ArgumentsImpl(csvResource), viewModelScope).getOrElse(null)
                ?: throw RuntimeException()
        }.flow
            .map { it.map { csvLine: CsvLine -> CsvLineView(csvLine.position.toString(), csvLine.columns) } }
            .cachedIn(viewModelScope)
    }
}