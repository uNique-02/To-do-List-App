package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskModel

class ItemAdapter(private val context: Context, private val dataset: List<TaskModel>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        //val cardView: CardView = view.findViewById(R.id.item_title)
            val checkBox: CheckBox = view.findViewById(R.id.checkbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        print("Enter oncreate ")
        return ItemAdapter.ItemViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
            holder.checkBox.text = (item.getTitle())
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}