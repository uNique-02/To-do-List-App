package com.example.myapplication.model

import androidx.recyclerview.widget.RecyclerView

class CardModel {
    var category: String = ""
    lateinit var taskModelList: List<TaskModel>

    constructor(category: String, taskModelList: List<TaskModel>) {
        this.category = category
        this.taskModelList = taskModelList
    }
    constructor(){

    }

    //getters && setters

    fun getCategoryText(): String{
        return category
    }
    fun getChildTaskModel(): List<TaskModel>{
        return taskModelList
    }
    fun setCategoryText(text: String){
        category = text
    }
    fun setchildTaskModel(taskModelList: List<TaskModel>){
        this.taskModelList = taskModelList
    }



}