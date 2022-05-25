package com.example.messageapp.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messageapp.databinding.MessageRecyclerItemBinding
import com.example.messageapp.db.sqldb.Message
import com.example.messageapp.ui.viewmodels.MainViewModel

class MessageAdapter(private val model: MainViewModel):
    RecyclerView.Adapter<MessageAdapter.ViewHolder>()
{

    inner class ViewHolder(private val binding: MessageRecyclerItemBinding):
        RecyclerView.ViewHolder(binding.root)
        {
            fun bindItem(message: Message)
            {
                binding.tvMessageTitle.text = message.title
                binding.tvMessageBody.text = message.body
            }

            fun startListener(message: Message)
            {
                binding.btnDeleteMsg.setOnClickListener {
                    model.deleteMessage(message)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MessageRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageList = model.getMessageList()
        val message = messageList[position]
        holder.bindItem(message)
        holder.startListener(message)
    }

    override fun getItemCount(): Int {
        return model.getMessageList().size
    }
}