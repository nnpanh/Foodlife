package com.example.foodlife.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.foodlife.R

class DetailMoreMenu: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.menu_detail)
        dialog.setCancelable(true)

        return dialog
    }

    companion object {
        const val TAG = "RecipeDetailMoreMenu"
    }
}
