package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.CardModel
import com.example.myapplication.model.TaskModel


class CardAdapter(private val context: Context, private val cardList: List<CardModel>, listener: ItemAdapter.OnItemCheckedListener):
    RecyclerView.Adapter<CardAdapter.CardViewHolder>(){
    var listener = listener

    class CardViewHolder(private val view: View, val cardList: List<CardModel>):RecyclerView.ViewHolder(view){
        lateinit var textCategoryView: TextView
        lateinit var childRView: RecyclerView
        lateinit var hideTasks: TextView
        lateinit var taskModelList: List<TaskModel>

        init {
            Log.e("KIMZ", "KIMNIQUEKIMNIQUEKIMNQIUEKIMNQIKIMQNIEU")
            textCategoryView = view.findViewById(R.id.progressHeading)
            childRView = view.findViewById(R.id.recyclerView1)
            hideTasks = view.findViewById(R.id.minButton1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        Log.e("PROPT", "Enter onCreate View Holder")
        //inflates the child_rv_layout.xml layout which is the layout that groups the tasks for each category
        // in a cardview that will be reused by the recycler view.

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

        //creates an adapter with a click listener for the child recycler views in the
        // nested recycler view that we will be making
        var childAdapter = ItemAdapter(context, cardList.get(position).taskModelList)
        childAdapter.setOnItemCheckedListener(listener)

        //important to have a layout manager
        holder.childRView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        holder.childRView.adapter = childAdapter
        holder.childRView.setHasFixedSize(true)
        childAdapter.notifyDataSetChanged()

        holder.hideTasks.setOnClickListener(){
            if(holder.hideTasks.text=="-"){
                holder.hideTasks.text = "+"
                holder.childRView.visibility = View.GONE
            }else{
                holder.hideTasks.text = "-"
                holder.childRView.visibility = View.VISIBLE
            }

        }

    }



}