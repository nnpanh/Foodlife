package com.example.foodlife.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddToPlanAdapter
import com.example.foodlife.adapters.BottomDialogAdapter
import com.example.foodlife.models.BottomDialogOption
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddToPlanBottomDialog : DialogFragment() {
    var rvAdapter: AddToPlanAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_add_to_plan,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOption = mutableListOf(
            BottomDialogOption(R.drawable.salad, "Breakfast"),
            BottomDialogOption(R.drawable.sandwich, "Lunch"),
            BottomDialogOption(R.drawable.pasta, "Dinner"),
            BottomDialogOption(R.drawable.lolipop, "Snack")
        )
        /**
         * Adapter for 4 meals
         */
         rvAdapter= AddToPlanAdapter(){ itemClicked ->
            listOption.filter { it.option == itemClicked.option }.forEach { itemInList ->
                itemInList.selected = !itemInList.selected
            }
             rvAdapter?.updateData(listOption)
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvChoose)
        rv.apply{
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        rvAdapter?.updateData(listOption)


        /**
         * Click on the -> to open calendar (select day)
         */
        view.findViewById<ImageView>(R.id.ivSelect).setOnClickListener{
            CalendarPopUp().show(parentFragmentManager,CalendarPopUp.TAG)
        }
        /**
         * Click on continue
         */
        view.findViewById<TextView>(R.id.btnContinue).setOnClickListener {
            var selectedAny = false
            listOption.forEach(){
                if (it.selected) selectedAny = true
            }
            if (!selectedAny) {
                val alertDialog = AlertDialog.Builder(this.context)
                alertDialog.setTitle("Please select at least one meal")
                alertDialog.show()
            }
            else {
                val bundle = Bundle()
                listOption.forEach(){
                    bundle.putBoolean(it.option, it.selected)
                }
                /**
                 * Return bundle included 4 meals
                 * Type: (String:Boolean)
                 * Example: "Breakfast" : true
                 */
                setFragmentResult("result", bundle)
                dismiss()
            }
        }
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