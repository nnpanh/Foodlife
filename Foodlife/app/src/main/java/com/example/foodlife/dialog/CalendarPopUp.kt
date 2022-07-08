package com.example.foodlife.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.foodlife.R

class CalendarPopUp : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.dialog_calendar)
        dialog.setCancelable(true)

        val image = dialog.findViewById<ImageView>(R.id.imgCalendar)
        image.setOnClickListener {
           image.setImageResource(R.drawable.catcool)
        }

        return dialog
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}