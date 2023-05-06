package com.task.noteapp.core.data

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import android.os.DeadObjectException
import com.task.noteapp.BuildConfig

open class BaseLocalDataSource {
    suspend fun <T> performDatabaseOperation(
        call: suspend () -> T
    ): DataResult<T, String> {
        return try {
            Success(call.invoke())
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG)
                exception.printStackTrace()
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): DataResult<Nothing, String> {
        return when (exception) {
            is SQLiteException -> {
                Error(
                    "Table creation error: Invalid table name or table fields not properly defined."
                )
            }
            is StaleDataException -> {
                Error(
                    "Query results are stale: Please re-run the query or update the database."
                )
            }
            is IllegalArgumentException -> {
                Error(
                    "Invalid query parameters: Please use correct data types or properly configure the parameters."
                )
            }
            is DeadObjectException -> {
                Error(
                    "Database transaction error: Database thread stopped while transaction was executing."
                )
            }
            else -> Error(exception.message ?: "An error accrued.")
        }
    }
}