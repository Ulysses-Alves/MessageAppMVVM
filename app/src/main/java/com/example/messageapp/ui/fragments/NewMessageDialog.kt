package com.example.messageapp.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.messageapp.R
import com.example.messageapp.databinding.NewMessageDialogBinding
import com.example.messageapp.ui.viewmodels.MainViewModel

class NewMessageDialog(private val model: MainViewModel) :
    DialogFragment()
{
    private var _binding: NewMessageDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        _binding = NewMessageDialogBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireContext(), R.style.RoundedCornersDialog)

        builder.setView(binding.root)
            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener(){ _, _ ->
        })
            .setPositiveButton(R.string.create, DialogInterface.OnClickListener(){ _, _ ->
                model.messageTitle = binding.etMessageTitle.text.toString()
                model.messageBody = binding.etMessageBody.text.toString()
                model.createMessage()
            })

        return builder.create()
    }

    companion object{
        const val TAG = "Dialog"
    }
}