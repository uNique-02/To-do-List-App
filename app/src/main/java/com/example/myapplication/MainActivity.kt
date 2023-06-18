package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CardAdapter
import com.example.myapplication.adapter.ItemAdapter
import com.example.myapplication.data.Datasource
import com.example.myapplication.model.CardModel
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemCheckedListener {
    lateinit var childList:ArrayList<TaskModel>
    private lateinit var db: DatabaseHandler
    lateinit var cardList: ArrayList<CardModel>
    lateinit var INPList: ArrayList<TaskModel>
    lateinit var OverdueList: ArrayList<TaskModel>
    lateinit var CompletedList: ArrayList<TaskModel>
    lateinit var parentRecyclerView: RecyclerView
    lateinit var parentAdapter: CardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        childList = ArrayList<TaskModel>()
        cardList = ArrayList<CardModel>()
        INPList = ArrayList<TaskModel>()
        OverdueList = ArrayList<TaskModel>()
        CompletedList = ArrayList<TaskModel>()
        parentRecyclerView = findViewById(R.id.parentRV)

        loadUI()
    }

    fun loadUI(){
        //open database and laod tasks to the recycler view
        Log.e("LOAD UI", "ENTERED LOAD Contacts.Intents.UI")
        db = DatabaseHandler(this)
        db.openDB()


        val myInPDataset = Datasource(db).loadINPTasks(0)
        val myCompletedDataset = Datasource(db).loadINPTasks(1)
        val myOverdueDataset = Datasource(db).loadINPTasks(2)
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

        cardList.add(CardModel("In Progress", myInPDataset))
        cardList.add(CardModel("Overdue", myOverdueDataset))
        cardList.add(CardModel("Completed", myCompletedDataset))

        Log.e("Card List size", cardList.size.toString())

        parentAdapter = CardAdapter(this, cardList, this as ItemAdapter.OnItemCheckedListener)
        Log.e("hays", "After parent adapter instantiate")
        parentRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        parentRecyclerView.adapter = parentAdapter
        parentAdapter.notifyDataSetChanged()
        parentRecyclerView.setHasFixedSize(true)

       /* var adapter2 = ItemAdapter(this, myCompletedDataset)
        adapter2.setOnItemCheckedListener(this as ItemAdapter.OnItemCheckedListener)
        recyclerView2.adapter = adapter2
        recyclerView2.setHasFixedSize(true)*/

        val newTaskBtn = findViewById<FloatingActionButton>(R.id.newTaskBtn)
        newTaskBtn.setOnClickListener(){
            val requestCode = 123
            val intentNew = Intent(this, NewtaskActivity::class.java)
            Log.e("Array list size", childList.size.toString())
            intentNew.putExtra("array", childList)
            startActivityForResult(intentNew, requestCode)
        }
    }

    override fun onItemChecked(ID: Int, position: Int, isChecked: Boolean) {
        Log.e("Position", position.toString())
        Log.e("Ischecked?", isChecked.toString())
        Log.e("IDENTIFICATION", ID.toString())
        var mark = if (isChecked) 1 else 0
        db.updateMark(ID, mark)
        //parentAdapter.notifyDataSetChanged()
        loadUI()
        Log.e("CLICKED", "CLICKEC CLICKED CLIEKC LCIEKC LKCE")
        //Log.e("Checked checkbox", todoItems[position].getTitle())

    }

}