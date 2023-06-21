package com.example.myapplication.model

import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class CardModelTest {

    var cardModel: CardModel = CardModel()

    @Before
    fun instantiate(){
        cardModel = CardModel()
    }

    @Test
    fun getAndSetCategoryText() {
        val expected = "Test String"
        cardModel.setCategoryText(expected)
        val actual: String = cardModel.getCategoryText()
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun getAndSetTaskModelList() {
        val task = TaskModel("Title sample",
            "Description Sample",
            "10-20-2022 11:56",
            "10-20-2022 11:57",
            0,
            0)
        val list = listOf<TaskModel>(task, task)
        val expected = list
        cardModel.setchildTaskModel(list)
        val actual = cardModel.getChildTaskModel()
        Assert.assertEquals(expected, actual)

    }
}