package com.rabobank.rabobankassignament.features.csv

import androidx.annotation.RawRes
import kotlin.Int

data class CsvResourceDto(@RawRes val resource: Int, val filename: String) {
    fun toCsvResource() = CsvResource(resource, filename)
}