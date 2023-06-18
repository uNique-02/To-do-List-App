package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ItemAdapter
import com.example.myapplication.data.Datasource
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

        //open database and laod tasks to the recycler view
        db = DatabaseHandler(this)
        db.openDB()


        val myInPDataset = Datasource(db).loadINPTasks(0)
        val myCompletedDataset = Datasource(db).loadINPTasks(1)
        Log.e("Database name", db.databaseName)
        Log.e("Task Title", "KIM CUTE - main acti")
        Log.e("List size", myInPDataset.size.toString())

        for(item in myInPDataset){
            Log.e("Task Title", item.getTitle())
            Log.e("Task description", item.getDescription())
            Log.e("reminder date", item.getReminder())
            Log.e("Due date", item.getDue())
            Log.e("Resource ID", item.getStringResourceID().toString())
        }

        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView1)
        val recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView1.adapter = ItemAdapter(this, myInPDataset)
        print("After adapter ")
        recyclerView1.setHasFixedSize(true)

        recyclerView2.adapter = ItemAdapter(this, myCompletedDataset)
        print("After adapter ")
        recyclerView2.setHasFixedSize(true)

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