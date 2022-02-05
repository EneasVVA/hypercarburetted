package com.rabobank.rabobankassignament.features.csv

data class CsvLineDto(val position: Int, val data: String) {
    fun toCsvLine(delimiter: String) = CsvLine(
            position = position,
            columns = data.split(delimiter)
        )
}
