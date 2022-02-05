package com.rabobank.rabobankassignament.features.csv

import androidx.annotation.RawRes
import kotlin.Int

data class CsvResourceView(@RawRes val resource: Int, val filename: String, val selected: Boolean = false)