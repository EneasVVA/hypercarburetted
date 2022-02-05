package com.rabobank.rabobankassignament.core.platform

import android.content.Context
import android.util.TypedValue
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.core.functional.Either
import com.rabobank.rabobankassignament.core.exception.Failure
import com.rabobank.rabobankassignament.features.csv.CsvResourceDto
import java.lang.reflect.Field
import javax.inject.Inject

class AndroidService
@Inject constructor(val context: Context) {
    fun getResourcesList(): Either<Failure, List<CsvResourceDto>> {
        val csvResources: MutableList<CsvResourceDto> = ArrayList()
        val fields: Array<Field> = R.raw::class.java.fields

        fields.forEach {
            try {
                val resourceId = it.getInt(it)
                val typedValue = TypedValue()
                context.resources.getValue(resourceId, typedValue, true)

                csvResources.add(
                    CsvResourceDto(
                        resource = resourceId,
                        filename = typedValue.string.toString()
                    )
                )
            } catch (e: Exception) {
                Either.Left(Failure.CsvResourcesCannotBeListedFailure)
            }
        }

        return Either.Right(csvResources)
    }

}