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
import com.example.foodlife.adapters.SelectCollectionAdapter
import com.example.foodlife.models.BottomDialogOption
import com.example.foodlife.view_models.CollectionViewModel

class SelectCollectionDialog : DialogFragment() {
    private var rvAdapter: SelectCollectionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_select_collection,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
         * Adapter for collections:
         * Return: collection title in "collection"
         */
        rvAdapter= SelectCollectionAdapter{ itemClicked ->
            val bundle = Bundle().apply {
                putString("collection", itemClicked.option)
            }
            setFragmentResult("request_key", bundle)
            dismiss()
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvChooseCollection)
        rv.apply{
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        rvAdapter?.updateData(listOption)

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
        const val TAG = "SelectCollectionDialog"
    }
}