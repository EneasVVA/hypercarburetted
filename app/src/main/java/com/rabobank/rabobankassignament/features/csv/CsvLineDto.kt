package com.rabobank.rabobankassignament.features.csv

import com.rabobank.rabobankassignament.core.extension.parseCsv

data class CsvLineDto(val position: Int, val data: String) {
    fun toCsvLine(delimiter: String, enclosing: Char) = CsvLine(
        position = position,
        columns = data.parseCsv(delimiter, enclosing)
    )
}
