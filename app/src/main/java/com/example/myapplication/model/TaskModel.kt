package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.*

class TaskModel() : Serializable, Parcelable {
    private lateinit var taskTitle: String
    private lateinit var description: String
    private lateinit var dateReminder: String
    private lateinit var dateDue: String
    private var mark: Int = 0
    private var stringResourceId: Int = 13
    var isChecked: Boolean = true

    constructor(parcel: Parcel) : this() {
        taskTitle = parcel.readString()!!
        description = parcel.readString()!!
        dateReminder = parcel.readString()!!
        dateDue = parcel.readString()!!
        mark = parcel.readInt()
        stringResourceId = parcel.readInt()
        isChecked = parcel.readByte() != 0.toByte()
    }

    constructor(
        taskTitle: String,
        description: String,
        dateReminder: String,
        dateDue: String,
        mark: Int,
        stringResourceId: Int
    ) : this() {
        this.taskTitle = taskTitle
        this.description = description
        this.dateReminder = dateReminder
        this.dateDue = dateDue
        this.mark = mark
        this.stringResourceId = stringResourceId
    }

    fun getTitle(): String {
        return taskTitle
    }

    fun setTitle(title: String) {
        taskTitle = title
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(descriptionP: String) {
        description = descriptionP
    }

    fun getReminder(): String {
        return dateReminder
    }

    fun setReminder(reminder: String) {
        dateReminder = reminder
    }

    fun getDue(): String {
        return dateDue
    }

    fun setDue(due: String) {
        dateDue = due
    }

    fun getMark(): Int {
        return mark
    }

    fun setMark(markP: Int) {
        mark = markP
    }

    fun getStringResourceID(): Int {
        return stringResourceId
    }

    fun setStringResourceID(id: Int) {
        stringResourceId = id
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(taskTitle)
        p0.writeString(description)
        p0.writeString(dateReminder)
        p0.writeString(dateDue)
        p0.writeInt(mark)
        p0.writeInt(stringResourceId)
    }

    companion object CREATOR : Parcelable.Creator<TaskModel> {
        override fun createFromParcel(parcel: Parcel): TaskModel {
            return TaskModel(parcel)
        }

        override fun newArray(size: Int): Array<TaskModel?> {
            return arrayOfNulls(size)
        }
    }


}