package com.rabobank.rabobankassignment.features.csv

import androidx.annotation.RawRes

data class CsvResourceView(
    @RawRes val resource: Int,
    val filename: String,
    val selected: Boolean = false
)