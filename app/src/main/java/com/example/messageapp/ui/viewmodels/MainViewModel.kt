package com.example.messageapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messageapp.db.sqldb.Message
import com.example.messageapp.repos.MessageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var msgRepo: MessageRepo): ViewModel()
{
    var messageTitle: String = ""
    var messageBody: String = ""

    private val currentDbSize: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun getLiveData(): LiveData<Int>
    {
        return currentDbSize
    }

    fun createMessage()
    {
        viewModelScope.launch {
            msgRepo.insertMessage(Message(0,messageTitle,messageBody))
            messageTitle = ""
            messageBody = ""
        }
        currentDbSize.value = getMessageList().size
    }

    fun getMessageList(): ArrayList<Message>
    {
        var messageList = ArrayList<Message>()

        viewModelScope.launch {
            messageList = msgRepo.getMessages()
        }

        return messageList
    }

    fun deleteMessage(message: Message)
    {
        viewModelScope.launch {
            msgRepo.deleteMessage(message)
        }
        currentDbSize.value = getMessageList().size
    }
}