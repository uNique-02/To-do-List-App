package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler

class Datasource {

    lateinit var db: DatabaseHandler

    constructor(db: DatabaseHandler) {
        this.db = db
    }

    fun loadINPTasks(mark: Int): List<TaskModel> {

        var list = db.retreiveTasks(mark)

        return list
    }
}
