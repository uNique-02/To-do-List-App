package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.ItemAdapter
import com.example.myapplication.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val myDataset = Datasource().loadTasks()
        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView1.adapter = ItemAdapter(this, myDataset)
        print("After adapter ")
        recyclerView1.setHasFixedSize(true)

    }
}