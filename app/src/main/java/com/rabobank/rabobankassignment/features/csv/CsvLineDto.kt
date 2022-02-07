package com.rabobank.rabobankassignment.features.csv

import com.rabobank.rabobankassignment.core.extension.parseCsv

data class CsvLineDto(val position: Int, val data: String) {
    fun toCsvLine(delimiter: String, enclosing: Char) = CsvLine(
        position = position,
        columns = data.parseCsv(delimiter, enclosing)
    )
}
