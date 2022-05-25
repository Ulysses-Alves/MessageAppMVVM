package com.example.messageapp.db.sqldb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.messageapp.util.Constants.MessageEntry.DATABASE_NAME
import com.example.messageapp.util.Constants.MessageEntry.DATABASE_VERSION
import com.example.messageapp.util.Constants.MessageEntry.MESSAGE_BODY
import com.example.messageapp.util.Constants.MessageEntry.MESSAGE_ID
import com.example.messageapp.util.Constants.MessageEntry.MESSAGE_TITLE
import com.example.messageapp.util.Constants.MessageEntry.TABLE_NAME


const val SQL_CREATE_TABLE =
    "CREATE TABLE $TABLE_NAME (" +
            "$MESSAGE_ID INTEGER PRIMARY KEY," +
            "$MESSAGE_TITLE TEXT," +
            "$MESSAGE_BODY TEST)"

const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

class SqlHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }

    fun insertToDatabase(message: Message): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(MESSAGE_TITLE, message.title)
            put(MESSAGE_BODY, message.body)
        }

        return db.insert(TABLE_NAME, null, values)
    }

    fun deleteFromDatabase(message: Message)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, MESSAGE_ID + "=" + message.id, null)
    }

    fun getMessageList(): ArrayList<Message>
    {
        val db = this.readableDatabase
        val selectAll = "SELECT * FROM $TABLE_NAME"
        val messageList: ArrayList<Message> = ArrayList()

        val cursor = db.rawQuery(selectAll, null)

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(MESSAGE_ID))
                val title = getString(getColumnIndexOrThrow(MESSAGE_TITLE))
                val body = getString(getColumnIndexOrThrow(MESSAGE_BODY))

                messageList.add(Message(id, title, body))
            }
        }
        return messageList
    }
}