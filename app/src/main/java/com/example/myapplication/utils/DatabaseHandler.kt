package com.example.myapplication.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.myapplication.model.TaskModel

class DatabaseHandler : SQLiteOpenHelper {

    companion object {
        private final val VERSION = 1
        private final val NAME = "todoListDB"
        private final val TODO_TABLE = "todoListTable"
        private final val ID = "id"
        private final val TASK_TITLE = "taskTitle"
        private final val TASK_DESCRIPTION = "taskDescription"
        private final val MARK = "mark"
        private final val REMINDER = "reminderDate"
        private final val DUE = "dueDate"
        private final val CREATE_TODO_TABLE =
            "CREATE TABLE " + TODO_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TASK_TITLE + " TEXT, " + TASK_DESCRIPTION + " TEXT, " + MARK + " INTEGER, " +
                    REMINDER + " DATETIME, " + DUE + " DATETIME " + ")"
    }


    var db: SQLiteDatabase = this.readableDatabase

    constructor(
        context: Context?
    ) : super(context, NAME, null, VERSION) {
        this.openDB()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TODO_TABLE)
        //add non-null asserted call
        this.db = db!!
    }

    override fun onUpgrade(db: SQLiteDatabase?, VERSION1: Int, VERSION2: Int) {
        //use a safe call of the instance of the database
        db?.execSQL("DROP TABLE IF EXISTS" + TODO_TABLE)
        onCreate(db)
    }

    public fun openDB() {
        db = this.writableDatabase
    }

    public fun insert(task: TaskModel) {

        var cv = ContentValues()
        cv.put(TASK_TITLE, task.getTitle())
        cv.put(TASK_DESCRIPTION, task.getDescription())
        cv.put(MARK, task.getMark())
        cv.put(REMINDER, task.getReminder())
        cv.put(DUE, task.getDue())

        //call getBottomMostEntry() to get the last ID used in the database
        // that will be used as the ID of the task that will be inserted
        var id = getBottomMostEntry();
        Log.e("HAPHAZARDLY", id.toString())
        task.setStringResourceID(id + 7);

        db.insert(TODO_TABLE, null, cv)

        //never forget to close the database for proetction and optimization
        db.close()
    }

    @SuppressLint("Range")
    public fun retreiveTasks(mark: Int): List<TaskModel> {
        var taskList: ArrayList<TaskModel> = ArrayList<TaskModel>()
        var cursorV: Cursor? = null

        Log.e("prompt", "Entered retreive task function")
        db.beginTransaction()
        try {
            Log.e("prompt", "Entered retreive task function - try")
           // if (mark == 0) {
                cursorV = db.query(TODO_TABLE, null, MARK + "=" + mark, null, null, null, null, null)
           /* } else {
                cursorV = db.query(TODO_TABLE, null, MARK + "=1", null, null, null, null, null)

            }*/
            if (cursorV != null) {
 //               Log.e("prompt", "Entered retreive task function - try if")
                if (cursorV.moveToFirst()) {
 //                   Log.e("prompt", "Entered retreive task function - try if if")
                    do {
 //                       Log.e("prompt", "Entered retreive task function - try if if do")
                        var new: TaskModel = TaskModel("", "", "", "", 0, 0)
                        new.setTitle(cursorV.getString(cursorV.getColumnIndex(TASK_TITLE)))
                        new.setDescription(
                            cursorV.getString(
                                cursorV.getColumnIndex(
                                    TASK_DESCRIPTION
                                )
                            )
                        )
                        new.setReminder(cursorV.getString(cursorV.getColumnIndex(REMINDER)))
                        new.setDue(cursorV.getString(cursorV.getColumnIndex(DUE)))
                        new.setMark((cursorV.getString(cursorV.getColumnIndex(MARK))).toInt())
                        new.setStringResourceID((cursorV.getString(cursorV.getColumnIndex(ID))).toInt())

                        // new.let { Log.e("Task Title", it.getTitle()) }
                        // new.let { Log.e("Task description", it.getDescription()) }
                        // new.setMark(cursorV.getInt(cursorV.getColumnIndex(MARK)))
                        new.let {
                            taskList.add(it)
                         /*   Log.e("Task Title", new.getTitle())
                            Log.e("Task description", new.getDescription())
                            Log.e("reminder date", new.getReminder())*/

                        }

                    } while (cursorV.moveToNext())
                }
            }
        } finally {
            db.endTransaction()
            cursorV?.close()
        }
        return taskList
    }

    public fun updateMark(id: Int, mark: Int) {
       // openDB()
        var cv = ContentValues()
        cv.put(MARK, mark)
        db.update(TODO_TABLE, cv, ID + "=?", arrayOf(id.toString()))
        //db.close()
    }

    public fun updateTask(id: Int, taskTitle: String, taskDescription: String, reminder: String, due: String, mark: Int) {
        var cv = ContentValues()
        cv.put(TASK_TITLE, taskTitle)
        cv.put(TASK_DESCRIPTION, taskDescription)
        cv.put(REMINDER, reminder)
        cv.put(DUE, due)
        cv.put(MARK, mark)
        db.update(TODO_TABLE, cv, ID + "=?", arrayOf(id.toString()))
    }

    public fun deleteTask(id: Int) {
        Log.e("Delete", "Entered delete")
        Log.e("Delete ID value: ", id.toString())
        db.delete(TODO_TABLE, ID + "=?", arrayOf(id.toString()))
    }

    @SuppressLint("Range")
    fun getBottomMostEntry(): Int {
        val db: SQLiteDatabase = this.readableDatabase

        // Define the columns you want to retrieve
        val columns = arrayOf("column_name")

        // Query the database and retrieve the bottommost entry
        val cursor: Cursor? = db.query(
            TODO_TABLE,
            null,
            null,
            null,
            null,
            null,
            ID + " DESC",  // Sort in descending order based on the column_name
            "1"                   // Limit to 1 row
        )

        var bottomEntry: Int = 0

        cursor?.let {
            if (cursor.moveToFirst()) {
                bottomEntry = cursor.getString(cursor.getColumnIndex(ID)).toInt()
            }
            cursor.close()
        }



        return bottomEntry
    }

}