package com.rabobank.rabobankassignament.features.csv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.InputStream
import java.io.LineNumberReader

class CsvReaderLocalDataSource<R : Any>(private val inputStreamOpener: () -> InputStream, private val transform: (CsvLineDto) -> R) : PagingSource<Int, R>() {

    private fun readFile(block: (reader: LineNumberReader) -> Unit) =
        LineNumberReader(inputStreamOpener().bufferedReader()).use {
            block(it)
        }

    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let {
            val prevKey = state.closestPageToPosition(it)?.prevKey
            val nextKey = state.closestPageToPosition(it)?.nextKey

            if(prevKey != null)
                return prevKey - 1
            if(nextKey != null)
                return nextKey + 1

            return null
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        val list = mutableListOf<CsvLineDto>()
        readFile {
            for(i in it.lineNumber until page * pageSize) {
                it.readLine() ?: return@readFile // skip line for moving pointer
            }

            for(i in it.lineNumber until (page + 1) * pageSize) {
                it.readLine()?.let { line ->
                    list += CsvLineDto(i, line)
                } ?: return@readFile
            }
        }

        return LoadResult.Page(
            list.map(transform),
            if(page > 0) page - 1 else null,
            if(list.size == pageSize) page + 1 else null
        )
    }
}