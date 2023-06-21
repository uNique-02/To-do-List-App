package com.example.myapplication.model

import android.widget.CheckBox
import com.example.myapplication.activities.MainActivity
import org.junit.Assert
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class TaskModelTest {
    var taskModelInstance = TaskModel()
    var main = MainActivity()

    @Test
    fun isChecked() {
        val checkBox = CheckBox(main)
        checkBox.setChecked(true)

        assertTrue(checkBox.isChecked())
    }

    @Test
    fun getAndSetTitle() {
        var expected = "Title"
        taskModelInstance.setTitle(expected)
        var actual = taskModelInstance.getTitle()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getAndSetDescription() {
        var expected = "Short Description"
        taskModelInstance.setDescription(expected)
        var actual = taskModelInstance.getDescription()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getAndSetReminder() {
        var expected = "11-2-2023 1:56"
        taskModelInstance.setReminder(expected)
        var actual = taskModelInstance.getReminder()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getAndSetDue() {
        var expected = "11-2-2023 1:56"
        taskModelInstance.setDue(expected)
        var actual = taskModelInstance.getDue()
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun getAndSetMark() {
        var expected = 1
        taskModelInstance.setMark(expected)
        var actual = taskModelInstance.getMark()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun getAndSetStringResourceID() {
        var expected = 1
        taskModelInstance.setStringResourceID(expected)
        var actual = taskModelInstance.getStringResourceID()
        Assert.assertEquals(expected, actual)

    }


    @Test
    fun describeContents() {
    }

    @Test
    fun writeToParcel() {
    }
}