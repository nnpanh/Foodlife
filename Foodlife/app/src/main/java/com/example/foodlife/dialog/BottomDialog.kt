package com.example.foodlife.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.BottomDialogAdapter
import com.example.foodlife.models.BottomDialogOption
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_bottom,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        val listOption = mutableListOf(
            BottomDialogOption(R.drawable.bookmark_bold, "Save to collection"),
            BottomDialogOption(R.drawable.share_bold, "Share to other platforms"),
            BottomDialogOption(R.drawable.ic_plan_demo, "Add to my plan")
        )
        val rvAdapter = BottomDialogAdapter{
            Log.d("DetailMoreMenu", "Clicked: kekw")
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvChoose)
        rv.apply{
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        rvAdapter.updateData(listOption)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            it.setGravity(Gravity.BOTTOM)
        }
    }

    companion object {
        const val TAG = "RecipeDetailMoreMenu"
    }

}