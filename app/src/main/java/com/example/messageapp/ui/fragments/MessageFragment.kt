package com.example.messageapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.messageapp.R
import com.example.messageapp.databinding.MessageRecyclerviewBinding
import com.example.messageapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment: Fragment(R.layout.message_recyclerview)
{
    private val model: MainViewModel by viewModels()

    private var _binding: MessageRecyclerviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MessageRecyclerviewBinding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = MessageAdapter(model)

        model.getLiveData().observe(viewLifecycleOwner, Observer {
            binding.recycler.adapter = MessageAdapter(model)
        })

        binding.btnAddMessage.setOnClickListener {
            NewMessageDialog(model).show(parentFragmentManager, NewMessageDialog.TAG)
        }

        return binding.root
    }

}