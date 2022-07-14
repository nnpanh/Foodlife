package com.example.foodlife.dialog

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddToPlanAdapter
import com.example.foodlife.models.BottomDialogOption

class AddToCollectionDialog : DialogFragment() {
    var rvAdapter: AddToPlanAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_add_to_collection,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listOption = mutableListOf(
            BottomDialogOption(0, "Meat Lover"),
            BottomDialogOption(0, "Healthy"),
            BottomDialogOption(0, "Diet")
        )
        /**
         * Adapter for 4 meals
         */
        rvAdapter= AddToPlanAdapter{ itemClicked ->
            listOption.filter { it.option == itemClicked.option }.forEach { itemInList ->
                itemInList.selected = !itemInList.selected
            }
            rvAdapter?.updateData(listOption)
        }

        val rv = view.findViewById<RecyclerView>(R.id.RVColChoose)
        rv.apply{
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        rvAdapter?.updateData(listOption)
        view.findViewById<TextView>(R.id.BTNColChoose).setOnClickListener {
            var selectedAny = false
            listOption.forEach{
                if (it.selected) selectedAny = true
            }

            if (!selectedAny) {
                val textValue = HtmlCompat.fromHtml("<b>Warning</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                val textButton = HtmlCompat.fromHtml("<font color='#FF9C00'>OK</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
                val dialogBuilder = AlertDialog.Builder(activity!!)
                dialogBuilder.setMessage("Please select at least one meal!")
                    .setTitle(textValue)
                    .setCancelable(false)
                    .setPositiveButton(textButton) { dialog, _ ->
                        dialog.dismiss()

                    }
                    .create()
                    .show()
            }
            else {
                setFragmentResult("request_key", Bundle())
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setGravity(Gravity.BOTTOM)
        }
    }

    companion object {
        const val TAG = "RecipeDetailMoreMenu"
    }
}