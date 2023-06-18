package com.example.myapplication.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.model.TaskModel
import kotlinx.coroutines.NonDisposableHandle.parent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ItemAdapter(private val context: Context, private val dataset: List<TaskModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    lateinit var array: ArrayList<TaskModel>
    var onItemCheckedListenerV: OnItemCheckedListener? = null
    lateinit var datasetCopy: List<TaskModel>


    class ItemViewHolder(private val view: View, val dataset: List<TaskModel>) : RecyclerView.ViewHolder(view) {
        //val cardView: CardView = view.findViewById(R.id.item_title)

        var checkBox: CheckBox = view.findViewById(R.id.checkbox)

        /*init {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                Log.e("Set on click change", "Enter change listener")
                if (position != RecyclerView.NO_POSITION) {
                    Log.e("Set on click change", "Enter IF")
                    val todoItem = dataset[position]
                    Log.e("To do item", dataset[position].getDescription())

                    todoItem.isChecked = isChecked

                    Log.e("IS CHECKED TO DO ITEM", todoItem.isChecked.toString())
                    Log.e("Is checked - variable", isChecked.toString())
                    if(dataset[position].getMark()==1){
                        todoItem.isChecked=false
                    }
                    onItemCheckedListenerV?.onItemChecked(dataset[position].getStringResourceID(), position, todoItem.isChecked)
                }
            }
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        print("Enter oncreate ")
        return ItemAdapter.ItemViewHolder(adapterLayout, dataset)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.checkBox.text = (item.getTitle())
        if(item.getMark()==1){
            holder.checkBox.isChecked=true
        }

        holder.checkBox.text = (item.getTitle())

        holder.checkBox.setOnClickListener(){
            if(holder.checkBox.isChecked==true){
                holder.checkBox.isChecked=false
                onItemCheckedListenerV?.onItemChecked(item.getStringResourceID(), item.getMark(), holder.checkBox.isChecked)
            }

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
    }



}