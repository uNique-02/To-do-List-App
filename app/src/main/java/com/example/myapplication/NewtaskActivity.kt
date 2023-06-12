package com.example.myapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class NewtaskActivity : AppCompatActivity() {

    lateinit var reminderDateText: TextView
    lateinit var dueDateText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtask)
        supportActionBar?.hide()
        var date: String = ""
        var time: String = ""

        reminderDateText = findViewById(R.id.reminderDatetxt)
        val reminderDatePickerBtn = findViewById<LinearLayout>(R.id.dateReminderbtn)
        reminderDatePickerBtn.setOnClickListener(){
           showDatePickerDialog(reminderDateText)
        }
        dueDateText = findViewById(R.id.dueDatetxt)
        val dueDatePickerBtn = findViewById<LinearLayout>(R.id.dateDuebtn)
        dueDatePickerBtn.setOnClickListener(){
            showDatePickerDialog(dueDateText)
        }
        val cancelBtn = findViewById<Button>(R.id.cancel)
        cancelBtn.setOnClickListener(){
            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)
        }
        val saveBtn = findViewById<Button>(R.id.save)
        saveBtn.setOnClickListener(){
            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)
        }
    }

    fun showDatePickerDialog(view:TextView){
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
            Log.e("KIM NQIUE", formattedDate)
            view.setText(formattedDate)
            showTimePickerDialog(view)
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dateDialog.show()
    }

    fun showTimePickerDialog(view: TextView){
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
                view.setText(view.text.toString() + " " + time)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false // Set to 'true' for 24-hour format
        )
        timePickerDialog.show()
    }

}

