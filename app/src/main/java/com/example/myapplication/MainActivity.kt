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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var list:ArrayList<TaskModel> = ArrayList<TaskModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()



        val myDataset = Datasource().loadTasks()
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            val receivedList = data?.getParcelableArrayListExtra<Parcelable>("array")
            // Use the received list as needed
        }
    }
}