package com.example.foodlife.dialog

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddToPlanAdapter
import com.example.foodlife.models.BottomDialogOption
import com.example.foodlife.view_models.CollectionViewModel

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
//        val listOption = mutableListOf(
//            BottomDialogOption(R.drawable.img_collection, "Meat Lover"),
//            BottomDialogOption(R.drawable.col2, "Healthy"),
//            BottomDialogOption(R.drawable.col1, "Diet")
//        )

        /**
         * Dynamic list
         */
        val store = Navigation.findNavController(parentFragment?.view!!).getViewModelStoreOwner(R.id.mobile_navigation)
        val viewModel = ViewModelProvider(store)[CollectionViewModel::class.java]
        if (viewModel.colList.value.isNullOrEmpty()) {
            viewModel.loadCollection()
        }
        val listOption = mutableListOf<BottomDialogOption>()
        viewModel.colList.value?.forEach { collection ->
            collection.oldImg?.let{
                listOption.add(BottomDialogOption(collection.oldImg!!, collection.title))
            }?: run {
                val linkedBottomDialogOption = BottomDialogOption(0, collection.title)
                linkedBottomDialogOption.imgUrl = collection.img
                listOption.add(linkedBottomDialogOption)
            }
        }

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

        /**
         * Click on add new collection
         */

        view.findViewById<ImageView>(R.id.addToCollection).setOnClickListener {

            setFragmentResult("request_key", Bundle().apply {
                putBoolean("add", true)
            })
            dismiss()
        }
        /**
         * Click on continue
         */
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
                val bundle = Bundle()
                listOption.forEach(){
                    bundle.putBoolean(it.option, it.selected)
                }
                setFragmentResult("request_key", bundle)
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
        const val TAG = "AddToCollectionBottomDialog"
    }
}