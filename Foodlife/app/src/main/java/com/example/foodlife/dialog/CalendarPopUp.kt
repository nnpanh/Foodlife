package com.example.foodlife.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.DialogFragment
import com.example.foodlife.R

class CalendarPopUp : DialogFragment() {
    private var isShowMoreInfo = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_calendar)
        dialog.setCancelable(true)
        dialog.window?.let{
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }

        val imgCalendar =dialog.findViewById<ImageView>(R.id.imgCalendar)
        imgCalendar.setOnClickListener {
            dismiss()
        }

        val imageInfo = dialog.findViewById<ImageView>(R.id.ivMoreInfo)
        imageInfo.setOnClickListener {
            val showMoreGroup = dialog.findViewById<Group>(R.id.grMoreInfo)
            if (isShowMoreInfo){
                showMoreGroup.visibility = View.GONE
                isShowMoreInfo = false
            } else {
                showMoreGroup.visibility = View.VISIBLE
                isShowMoreInfo = true
            }

        }
        return dialog
    }

    companion object {
        const val TAG = "CalendarPopUp"
    }
}