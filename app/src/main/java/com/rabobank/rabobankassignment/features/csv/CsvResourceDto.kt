package com.rabobank.rabobankassignment.features.csv

import androidx.annotation.RawRes

data class CsvResourceDto(@RawRes val resource: Int, val filename: String) {
    fun toCsvResource() = CsvResource(resource, filename)
}