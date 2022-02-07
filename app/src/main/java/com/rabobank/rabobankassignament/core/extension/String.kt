package com.rabobank.rabobankassignament.core.extension

import org.apache.commons.csv.CSVFormat
import java.io.StringReader

fun String.parseCsv(delimiter: String? = null, quote: Char? = null): List<String>? =
    CSVFormat.DEFAULT.builder()
        .setDelimiter(delimiter)
        .setQuote(quote)
        .build()
        .parse(StringReader(this)).records.getOrNull(0)?.toList()
