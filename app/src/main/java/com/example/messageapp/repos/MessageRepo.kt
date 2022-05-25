package com.example.messageapp.repos

import com.example.messageapp.db.sqldb.Message
import com.example.messageapp.db.sqldb.SqlHelper
import javax.inject.Inject

class MessageRepo @Inject constructor(private val sqlHelper: SqlHelper)
{
    fun getMessages() = sqlHelper.getMessageList()

    fun insertMessage(message: Message) = sqlHelper.insertToDatabase(message)

    fun deleteMessage(message: Message) = sqlHelper.deleteFromDatabase(message)
}