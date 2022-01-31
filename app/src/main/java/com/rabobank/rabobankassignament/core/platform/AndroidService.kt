package com.rabobank.rabobankassignament.core.platform

import android.content.Context
import android.util.TypedValue
import com.rabobank.rabobankassignament.R
import com.rabobank.rabobankassignament.exception.Failure
import com.rabobank.rabobankassignament.features.csv.CsvResource
import com.rabobank.rabobankassignament.functional.Either
import java.lang.reflect.Field
import javax.inject.Inject

class AndroidService
@Inject constructor(private val context: Context) {
    fun getResourcesList(): Either<Failure, List<CsvResource>> {
        val csvResources: MutableList<CsvResource> = ArrayList()
        val fields: Array<Field> = R.raw::class.java.fields

        fields.forEach {
            try {
                val resourceId = it.getInt(it)
                val typedValue = TypedValue()
                context.resources.getValue(resourceId, typedValue, true)

                csvResources.add(
                    CsvResource(
                        resource = resourceId,
                        filename = typedValue.string.toString()
                    )
                )
            } catch (e: Exception) {
                Either.Left(Failure.CsvCannotBeListedFailure)
            }
        }

        return Either.Right(csvResources)
    }

}