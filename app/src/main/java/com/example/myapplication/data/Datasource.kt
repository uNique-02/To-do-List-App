package com.example.myapplication.data

import com.example.myapplication.R
import com.example.myapplication.model.Task

class Datasource {

    fun loadTasks(): List<Task> {
        return listOf<Task>(
            Task(R.string.affirmation1),
            Task(R.string.affirmation2),
            Task(R.string.affirmation3),
            Task(R.string.affirmation4),
           /* Task(R.string.affirmation5),
            Task(R.string.affirmation6),
            Task(R.string.affirmation7),
            Task(R.string.affirmation8),
            Task(R.string.affirmation9),
            Task(R.string.affirmation10) */
        )
    }
}
