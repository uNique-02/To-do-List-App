package com.example.myapplication.model

import java.io.Serializable
import java.util.*

class TaskModel:Serializable {
    private var taskTitle:String
    private var description:String
    private var dateReminder:String
    private var dateDue:String
    private var mark:Int = 0
    private var stringResourceId: Int = 0


    constructor(
        taskTitle: String,
        description: String,
        dateReminder: String,
        dateDue: String,
        mark: Int,
        stringResourceId: Int
    ) {
        this.taskTitle = taskTitle
        this.description = description
        this.dateReminder = dateReminder
        this.dateDue = dateDue
        this.mark = mark
        this.stringResourceId = stringResourceId
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

    fun getReminder():String{
        return dateReminder
    }
    fun setReminder(reminder:String){
        dateReminder=reminder
    }

    fun getDue():String{
        return dateDue
    }
    fun setDue(due:String){
        dateDue=due
    }
    fun getMark():Int{
        return mark
    }
    fun setMark(markP:Int){
        mark = markP
    }
    fun getStringResourceID(): Int{
        return stringResourceId
    }
    fun setStringResourceID(id: Int){
        stringResourceId = id
    }


}