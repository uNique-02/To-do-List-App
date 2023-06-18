package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler

class Datasource {

    lateinit var db: DatabaseHandler
    constructor(db: DatabaseHandler){
        this.db = db
    }

    fun loadTasks(): List<TaskModel> {

        var list = db.retreiveTask()

       // Log.e("Database name", db.databaseName)
       // Log.e("Task Title", "KIM CUTE - main Datasource")
       // Log.e("List size", list.size.toString())
        return list
    }
}
