package com.example.myapplication.data

import com.example.myapplication.activities.MainActivity
import com.example.myapplication.utils.DatabaseHandler
import org.junit.Assert
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class DatasourceTest {
    var main = MainActivity()
    @Test
    fun getAndSetDb() {
        val ds = Datasource()
        val db= DatabaseHandler(main)
        ds.db = db
        var actual = ds.db
        Assert.assertEquals(db, actual)
    }

    @Test
    fun loadINPTasks() {
    }
}