package com.example.foodlife.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.BottomDialogAdapter
import com.example.foodlife.models.BottomDialogOption
import com.google.android.material.bottomsheet.BottomSheetDialog

class DetailMoreMenu: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        /**
         * Ver 1: Round middle popup
         */
        val dialog = Dialog(this.requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.menu_detail)
        dialog.setCancelable(true)
        dialog.window?.let{
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }
        /**
         * Ver 2: Bottom dialog
         */
//        val dialog = BottomSheetDialog(this.requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.let{
//            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            it.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//        }
//        val rootView = this.requireActivity().layoutInflater.inflate(R.layout.dialog_bottom, null)
//
//        val listOption = mutableListOf(
//            BottomDialogOption(R.drawable.bookmark_bold, "Save to collection"),
//            BottomDialogOption(R.drawable.share_bold, "Share to other platforms"),
//            BottomDialogOption(R.drawable.ic_plan_demo, "Add to my plan")
//        )
//        val rvAdapter = BottomDialogAdapter{
//            Log.d("DetailMoreMenu", "Clicked: kekw")
//        }
//
//        val rv = rootView.findViewById<RecyclerView>(R.id.rvChoose)
//        rv.apply{
//            adapter = rvAdapter
//            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
//        }
//        rvAdapter.updateData(listOption)
//
//        dialog.setContentView(rootView)
//        dialog.setCancelable(true)
        return dialog
    }

    companion object {
        const val TAG = "RecipeDetailMoreMenu"
    }
}
