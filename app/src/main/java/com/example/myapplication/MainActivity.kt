package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ItemAdapter
import com.example.myapplication.data.Datasource
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var list:ArrayList<TaskModel> = ArrayList<TaskModel>()
    private lateinit var db: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        db = DatabaseHandler(this)
        db.openDB()



        val myDataset = Datasource(db).loadTasks()
        Log.e("Database name", db.databaseName)
        Log.e("Task Title", "KIM CUTE - main acti")
        Log.e("List size", myDataset.size.toString())

        for(item in myDataset){
            Log.e("Task Title", item.getTitle())
            Log.e("Task description", item.getDescription())
            Log.e("reminder date", item.getReminder())
            Log.e("Due date", item.getDue())
            Log.e("Resource ID", item.getStringResourceID().toString())
        }
        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView1.adapter = ItemAdapter(this, myDataset)
        print("After adapter ")
        recyclerView1.setHasFixedSize(true)

        val newTaskBtn = findViewById<FloatingActionButton>(R.id.newTaskBtn)
        newTaskBtn.setOnClickListener(){
            val requestCode = 123
            val intentNew = Intent(this, NewtaskActivity::class.java)
            Log.e("Array list size", list.size.toString())
            intentNew.putExtra("array", list)
            startActivityForResult(intentNew, requestCode)
        }

    }

}