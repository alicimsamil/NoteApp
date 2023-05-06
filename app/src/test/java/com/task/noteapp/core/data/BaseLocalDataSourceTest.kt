package com.task.noteapp.core.data

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import com.task.noteapp.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class BaseLocalDataSourceTest {

    private lateinit var dataSource: BaseLocalDataSource

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        dataSource = BaseLocalDataSource()
    }

    @Test
    fun perform_database_operation_should_return_success_with_correct_value() = runTest {
        val result = dataSource.performDatabaseOperation { 42 }
        assertTrue(result is Success)
        assertEquals(42, (result as Success).data)
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_SQLiteException() = runTest {
        val result = dataSource.performDatabaseOperation { throw SQLiteException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals("Table creation error: Invalid table name or table fields not properly defined.", it)
        }
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_StaleDataException() = runTest {
        val result = dataSource.performDatabaseOperation { throw StaleDataException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(
                "Query results are stale: Please re-run the query or update the database.",
                it
            )
        }
    }

    @Test
    fun perform_database_operation_should_return_correct_message_for_null_error_message() = runTest {
        val result = dataSource.performDatabaseOperation { throw IOException() }
        result.onFailure {
            assertEquals(
                "An error accrued.",
                it
            )
        }
    }
}