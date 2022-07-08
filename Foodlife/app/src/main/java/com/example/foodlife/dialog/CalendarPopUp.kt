package com.example.foodlife.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodlife.R

class CalendarPopUp:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val view = layoutInflater.inflate(R.layout.item_ingredients,null)
       return AlertDialog.Builder(requireContext())
            .setView(view)
            .create()
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}