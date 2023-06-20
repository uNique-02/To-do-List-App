package com.example.myapplication.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.TaskModel


class ItemAdapter(private val context: Context, private val dataset: List<TaskModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    lateinit var array: ArrayList<TaskModel>
    var onItemCheckedListenerV: OnItemCheckedListener? = null
    lateinit var datasetCopy: List<TaskModel>


    class ItemViewHolder(private val view: View, val dataset: List<TaskModel>) :
        RecyclerView.ViewHolder(view) {
        var card = view.findViewById<CardView>(R.id.card_container)
        var checkBox: CheckBox = view.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        //inflates the list_item.xml layout which is the layout for the individual tasks that will be reused by the recycler view.
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        print("Enter oncreate ")
        return ItemAdapter.ItemViewHolder(adapterLayout, dataset)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        //binds the data to the graphical elements
        val item = dataset[position]
        holder.checkBox.text = (item.getTitle())
        if (item.getMark() == 1) {
            holder.checkBox.isChecked = true
        } else if (item.getMark() == 2) {
            holder.checkBox.isEnabled = false
            holder.checkBox.setTextColor(ContextCompat.getColor(context, R.color.error))
        }

        holder.checkBox.text = (item.getTitle())

        //sets the listener for the checkboxes
        holder.checkBox.setOnClickListener() {
            onItemCheckedListenerV?.onItemChecked(
                item.getStringResourceID(),
                item.getMark(),
                holder.checkBox.isChecked
            )
        }

        holder.card.setOnClickListener(){
            onItemCheckedListenerV?.onItemClicked(item.getStringResourceID(), position, item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    public fun setOnItemCheckedListener(listener: OnItemCheckedListener) {
        onItemCheckedListenerV = listener
    }

    interface OnItemCheckedListener {
        fun onItemChecked(ID: Int, position: Int, isChecked: Boolean)
        fun onItemClicked(ID:Int, position:Int, task: TaskModel)
    }


}