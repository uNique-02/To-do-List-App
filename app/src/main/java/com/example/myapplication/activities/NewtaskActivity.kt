package com.example.myapplication.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.util.Log.d
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.model.TaskModel
import com.example.myapplication.utils.DatabaseHandler
import java.lang.reflect.InvocationTargetException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NewtaskActivity : AppCompatActivity() {

    lateinit var titleView: EditText
    lateinit var descriptionView: EditText
    lateinit var reminderDateText: TextView
    lateinit var dueDateText: TextView


    var titleInput: String = ""
    var descriptionInput: String = ""
    var reminderDateInput: Date? = null
   var dueDateInput: Date? = null

    lateinit var activityMain: MainActivity
    lateinit var intentThis: Intent
    lateinit var db: DatabaseHandler
    lateinit var saveBtn: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtask)
        supportActionBar?.hide()
        var date: String = ""
        var time: String = ""

        db= DatabaseHandler(this)


        intentThis = intent
        var todo: TaskModel? = intentThis.extras?.getParcelable("todoItem")

        titleView = findViewById(R.id.taskTitle)
        descriptionView = findViewById(R.id.taskDescription)
        saveBtn = findViewById<Button>(R.id.save)
        if(todo==null){
            saveBtn.isEnabled = false
            saveBtn.setTextColor(ContextCompat.getColor(this, R.color.disabled))
        }

        reminderDateText = findViewById(R.id.reminderDatetxt)
        val reminderDatePickerBtn = findViewById<LinearLayout>(R.id.dateReminderbtn)
        reminderDatePickerBtn.setOnClickListener() {
            showDatePickerDialog(reminderDateText, 0)
        }
        dueDateText = findViewById(R.id.dueDatetxt)
        val dueDatePickerBtn = findViewById<LinearLayout>(R.id.dateDuebtn)
        dueDatePickerBtn.setOnClickListener() {
            showDatePickerDialog(dueDateText, 1)
            Log.e("Due date input null?", (dueDateInput==null).toString())

        }
        val cancelBtn = findViewById<Button>(R.id.cancel)
        cancelBtn.setOnClickListener() {
            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)
        }

        saveBtn.setOnClickListener() {
            Log.e("Entered save button", "YUUUP")
            saveTaskToDatabase()
        }




        var delete: Button = findViewById<Button>(R.id.delete)

        if(todo!=null){
            titleView.setText(todo.getTitle())
            descriptionView.setText(todo.getDescription())
            reminderDateText.setText(todo.getReminder())
            dueDateText.setText(todo.getDue())
        }else{
            delete.isEnabled=false
            delete.setTextColor(ContextCompat.getColor(this, R.color.disabled))
        }

        delete.setOnClickListener(){
            deleteTask(todo?.getStringResourceID().toString().toInt())
        }


    }

    private fun deleteTask(ID: Int) {
        db = DatabaseHandler(applicationContext)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete task")
        builder.setMessage("Are you sure you want to proceed?")

        builder.setPositiveButton("OK") { dialog, which ->
            db.deleteTask(ID)
            Handler().postDelayed({
            }, 1000)

            val intentNew = Intent(this, MainActivity::class.java)
            startActivity(intentNew)
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTaskToDatabase(){
        titleInput = titleView.text.toString()
        descriptionInput = descriptionView.text.toString()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())


        Log.e("On save Title", titleInput)
        Log.e("On save DEscription", descriptionInput)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var reminderStr = ""
        var dueStr = ""

        if(reminderDateInput!=null){
            reminderStr = dateFormat.format(reminderDateInput)
        }
        if(dueDateInput!=null){
            dueStr = dateFormat.format(dueDateInput)
        }

        if(reminderDateInput==null){
            reminderStr = reminderDateText.text.toString()
        }
        if(dueDateInput==null){
            dueStr = dueDateText.text.toString()
        }

        Log.e("Description", descriptionInput)
        Log.e("Title", titleInput)

        if (descriptionInput != "" || titleInput != "" || dueDateInput != null) {
            var task = TaskModel(
                titleInput,
                descriptionInput,
                reminderStr,
                dueStr,
                0,
                0
            )

            //use formatter to parse dateTime entry in database to a LocalDateTime object
            // to check if the task is overdue or otherwise


            val dateTime = LocalDateTime.parse(dueStr, formatter)

            var todo: TaskModel? = intentThis.extras?.getParcelable("todoItem")

            Log.e("DATE-TIME", dateTime.toString() + " " +LocalDateTime.now())
            if(dateTime.isBefore(LocalDateTime.now())){
                todo?.setMark(2)
                task.setMark(2)
            }

            db = DatabaseHandler(applicationContext)
            intentThis = intent


            if(todo!=null){
                Log.e("TODO IS NOT NULL", "YUPP NOT NULL")
                db.updateTask(todo.getStringResourceID(), titleInput, descriptionInput, reminderStr, dueStr, todo.getMark())
            }else{
                db.insert(task)
            }

        }
        val intentNew = Intent(this, MainActivity::class.java)
        startActivity(intentNew)
    }

    fun showDatePickerDialog(view: TextView, binary: Int) {
        val calendar = Calendar.getInstance()
        var date: String = ""
        var time: String = ""
        var selectedDate: Calendar = Calendar.getInstance()
        var selectedTime: Calendar = Calendar.getInstance()

        //calls DatePickerDialog that shows a graphical calendar interface
        val dateDialog = DatePickerDialog(
            this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Do something with the selected date
                selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                // Handle the selected date here
                val formattedDate =
                    "${selectedDate.get(Calendar.YEAR)}-${selectedDate.get(Calendar.MONTH) + 1}-${
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                    }"

                view.setText(formattedDate)
                if (binary == 0) {
                    reminderDateInput = Date(
                        selectedDate.get(Calendar.YEAR) - 1900,
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                    )
                } else {
                    dueDateInput = Date(
                        selectedDate.get(Calendar.YEAR) - 1900,
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                    )
                }
                showTimePickerDialog(view, binary)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dateDialog.show()

    }

    fun showTimePickerDialog(view: TextView, binary: Int) {
        val calendar = Calendar.getInstance()
        var time: String = ""
        var selectedTime = Calendar.getInstance()
        //calls TimePickerDialog that shows a graphical clock interface
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

                if (binary == 0) {
                    reminderDateInput?.hours = selectedTime.get(Calendar.HOUR_OF_DAY)
                    reminderDateInput?.minutes = selectedTime.get(Calendar.MINUTE)
                } else {
                    dueDateInput?.hours = selectedTime.get(Calendar.HOUR_OF_DAY)
                    dueDateInput?.minutes = selectedTime.get(Calendar.MINUTE)
                    saveBtn.isEnabled = true
                    saveBtn.setTextColor(ContextCompat.getColor(this, R.color.error))
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







