package com.example.myapplication

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class NewtaskActivity : AppCompatActivity() {

    lateinit var titleView:EditText
    lateinit var descriptionView: EditText
    lateinit var reminderDateText: TextView
    lateinit var dueDateText: TextView


    var titleInput:String=""
    var descriptionInput:String=""
    lateinit var reminderDateInput:Date
    lateinit var dueDateInput:Date

    lateinit var activityMain: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtask)
        supportActionBar?.hide()
        var date: String = ""
        var time: String = ""

        titleView = findViewById(R.id.taskTitle)
        descriptionView = findViewById(R.id.taskDescription)

        reminderDateText = findViewById(R.id.reminderDatetxt)
        val reminderDatePickerBtn = findViewById<LinearLayout>(R.id.dateReminderbtn)
        reminderDatePickerBtn.setOnClickListener(){
           showDatePickerDialog(reminderDateText, 0)
        }
        dueDateText = findViewById(R.id.dueDatetxt)
        val dueDatePickerBtn = findViewById<LinearLayout>(R.id.dateDuebtn)
        dueDatePickerBtn.setOnClickListener(){
            showDatePickerDialog(dueDateText, 1)
        }
        val cancelBtn = findViewById<Button>(R.id.cancel)
        cancelBtn.setOnClickListener(){
            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)
        }
        val saveBtn = findViewById<Button>(R.id.save)
        saveBtn.setOnClickListener(){

            titleInput = titleView.text.toString()
            descriptionInput = descriptionView.text.toString()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

            Log.e("Description", descriptionInput)
            Log.e("Title", titleInput)

        if(descriptionInput!="" || titleInput!="" || dueDateInput!=null){
            var task = TaskModel(titleInput, descriptionInput, dateFormat.format(reminderDateInput), dateFormat.format(dueDateInput), 1, 0)
           // Log.e("REMINDER",dateFormat.format(reminderDateInput))
           // Log.e("DUE",dateFormat.format(dueDateInput))

            var db = DatabaseHandler(applicationContext)
                db.insert(task)



        }
            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)



        }
    }

    fun showDatePickerDialog(view:TextView, binary:Int){
        val calendar = Calendar.getInstance()
        var date:String = ""
        var time: String = ""
        var selectedDate: Calendar = Calendar.getInstance()
        var selectedTime: Calendar = Calendar.getInstance()
        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            // Do something with the selected date
            selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)
            // Handle the selected date here
            val formattedDate = "${selectedDate.get(Calendar.YEAR)}-${selectedDate.get(Calendar.MONTH) + 1}-${selectedDate.get(Calendar.DAY_OF_MONTH)}"

            view.setText(formattedDate)
            if(binary==0){
                reminderDateInput = Date(selectedDate.get(Calendar.YEAR) - 1900, selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH))
            }else{
                dueDateInput = Date(selectedDate.get(Calendar.YEAR) - 1900, selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH))
            }
             showTimePickerDialog(view, binary)
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dateDialog.show()
    }

    fun showTimePickerDialog(view: TextView, binary:Int){
        val calendar = Calendar.getInstance()
        var time: String = ""
        var selectedTime = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // Do something with the selected time
                selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                // Handle the selected time here
                val amPm = if (selectedTime.get(Calendar.AM_PM) == Calendar.AM) {
                    "AM"
                } else {
                    "PM"
                }
                time = hourOfDay.toString() + ":" + minute.toString() + " " + amPm

                if(binary==0){
                    reminderDateInput.hours = selectedTime.get(Calendar.HOUR_OF_DAY)
                    reminderDateInput.minutes = selectedTime.get(Calendar.MINUTE)
                }else{
                    dueDateInput.hours = selectedTime.get(Calendar.HOUR_OF_DAY)
                    dueDateInput.minutes = selectedTime.get(Calendar.MINUTE)
                }

                    //LocalTime.parse(time, formatter)
                view.setText(view.text.toString() + " " + time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false // Set to 'true' for 24-hour format
        )
        timePickerDialog.show()
    }

}

/*
private fun Intent.putParcelableArrayListExtra(key: String, arrayList: ArrayList<TaskModel>) {
    val parcelableArray = arrayList.toTypedArray().apply {
        putExtra(key, this)
    }
}*/






