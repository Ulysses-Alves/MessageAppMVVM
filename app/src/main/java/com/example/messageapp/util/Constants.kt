package com.example.messageapp.util

import android.provider.BaseColumns

object Constants
{
    object MessageEntry: BaseColumns
    {
        const val DATABASE_NAME = "messages"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "message_table"
        const val MESSAGE_ID = "id"
        const val MESSAGE_TITLE = "title"
        const val MESSAGE_BODY = "body"
    }
}