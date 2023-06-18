package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.model.TaskModel
import kotlinx.coroutines.NonDisposableHandle.parent


class ItemAdapter(private val context: Context, private val dataset: List<TaskModel>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    lateinit var array: ArrayList<TaskModel>
    var onItemCheckedListenerV: OnItemCheckedListener? = null
    lateinit var datasetCopy: List<TaskModel>


    class ItemViewHolder(private val view: View, val dataset: List<TaskModel>, val onItemCheckedListenerV: OnItemCheckedListener?) : RecyclerView.ViewHolder(view) {
        //val cardView: CardView = view.findViewById(R.id.item_title)

        var checkBox: CheckBox = view.findViewById(R.id.checkbox)

        init {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                Log.e("Set on click change", "Enter change listener")
                if (position != RecyclerView.NO_POSITION) {
                    Log.e("Set on click change", "Enter IF")
                    val todoItem = dataset[position]
                    Log.e("To do item", dataset[position].getDescription())
                    todoItem.isChecked = isChecked

                    onItemCheckedListenerV?.onItemChecked(dataset, position, isChecked)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        print("Enter oncreate ")
        return ItemAdapter.ItemViewHolder(adapterLayout, dataset, onItemCheckedListenerV)

    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.checkBox.text = (item.getTitle())
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    public fun setOnItemCheckedListener(listener: OnItemCheckedListener) {
        onItemCheckedListenerV = listener
    }

    interface OnItemCheckedListener {
        fun onItemChecked(todoItems: List<TaskModel>, position: Int, isChecked: Boolean)
    }

}