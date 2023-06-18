package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.model.CardModel
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler
import org.w3c.dom.Text



class CardAdapter(private val context: Context, private val cardList: List<CardModel>, listener: ItemAdapter.OnItemCheckedListener):
    RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    var listener = listener

    class CardViewHolder(private val view: View, val cardList: List<CardModel>):RecyclerView.ViewHolder(view){
        lateinit var textCategoryView: TextView
        lateinit var childRView: RecyclerView
        lateinit var taskModelList: List<TaskModel>

        init {
            Log.e("KIMZ", "KIMNIQUEKIMNIQUEKIMNQIUEKIMNQIKIMQNIEU")
            textCategoryView = view.findViewById(R.id.progressHeading)
            childRView = view.findViewById(R.id.recyclerView1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        Log.e("PROPT", "Enter onCreate View Holder")
        var cardLayoutAdapter = LayoutInflater.from(parent.context).inflate(R.layout.child_rv_layout, parent, false)
        Log.e("PROPT", "Enter onCreate View Holder")
        return CardAdapter.CardViewHolder(cardLayoutAdapter, cardList)
    }

    override fun getItemCount(): Int {
        Log.e("CARD LIST SIZE", cardList.size.toString())
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.textCategoryView.text = cardList[position].category
        Log.e("CATEGORY", cardList[position].category)
        var childAdapter = ItemAdapter(context, cardList.get(position).taskModelList)
        childAdapter.setOnItemCheckedListener(listener)
        holder.childRView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        holder.childRView.adapter = childAdapter
        holder.childRView.setHasFixedSize(true)
        childAdapter.notifyDataSetChanged()
        //holder.taskModelList = cardList[position].taskModelList

        /*var adapter1 = ItemAdapter(this, myInPDataset)
        adapter1.setOnItemCheckedListener(this as ItemAdapter.OnItemCheckedListener)
        parentRecyclerView.adapter = adapter1
        parentRecyclerView.setHasFixedSize(true)*/
    }



}