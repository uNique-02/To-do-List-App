package com.example.myapplication.model

import java.io.Serializable
import java.util.*

class TaskModel:Serializable {
    private var taskTitle:String
    private var description:String
    private var dateReminder:Date
    private var dateDue:Date
    private var mark:Int = 0

    constructor(
        taskTitle: String,
        description: String,
        dateReminder: Date,
        dateDue: Date,
        mark: Int
    ) {
        this.taskTitle = taskTitle
        this.description = description
        this.dateReminder = dateReminder
        this.dateDue = dateDue
        this.mark = mark
    }
    fun getTitle():String{
        return taskTitle
    }
    fun setTitle(title:String){
        taskTitle=title
    }

    fun getDescription():String{
        return description
    }
    fun setDescription(descriptionP:String){
        description=descriptionP
    }

    fun getReminder():Date{
        return dateReminder
    }
    fun setReminder(reminder:Date){
        dateReminder=reminder
    }

    fun getDue():Date{
        return dateDue
    }
    fun setDue(due:Date){
        dateDue=due
    }
    fun getMark():Int{
        return mark
    }
    fun setMark(markP:Int){
        mark = markP
    }


}