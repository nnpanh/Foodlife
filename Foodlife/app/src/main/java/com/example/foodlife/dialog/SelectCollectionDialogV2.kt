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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.*
import com.example.foodlife.models.BottomDialogOption
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Recipe
import com.example.foodlife.view_models.CollectionViewModel
import com.google.android.material.snackbar.Snackbar

class SelectCollectionDialogV2 : DialogFragment() {
    private var rvAdapter: SelectCollectionAdapterV2? = null
    //private lateinit var navController: NavController
    //private var rvRecipeAdapter: CollectionRecipeAdapter? = null
    private var rvRecipeAdapter: CollectionAddToPlanAdapter? = null
    private var contextView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_select_collection_v2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navController = Navigation.findNavController(view)
        /**
         * Dynamic list
         */
        val store = Navigation.findNavController(parentFragment?.view!!).getViewModelStoreOwner(R.id.mobile_navigation)
        val viewModel = ViewModelProvider(store)[CollectionViewModel::class.java]
        if (viewModel.colList.value.isNullOrEmpty()) {
            viewModel.loadCollection()
        }
        /*val listOption = mutableListOf<BottomDialogOption>()
        viewModel.colList.value?.forEach { collection ->
            collection.oldImg?.let{
                listOption.add(BottomDialogOption(collection.oldImg!!, collection.title))
            }?: run {
                val linkedBottomDialogOption = BottomDialogOption(0, collection.title)
                linkedBottomDialogOption.imgUrl = collection.img
                listOption.add(linkedBottomDialogOption)
            }
        }*/

        /**
         * Adapter for collections:
         * Return: collection title in "collection"
         */
        contextView = view
        rvAdapter= SelectCollectionAdapterV2{ itemClicked ->
            val newList = viewModel.colList.value
            newList!!.forEachIndexed { index, collection ->
                if (itemClicked.title==collection.title) {
                    rvRecipeAdapter?.updateData(viewModel.colList.value!![index].recipes)
                    rvRecipeAdapter?.notifyDataSetChanged()
                }
            }
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvChooseCollection)
        rv.apply{
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
        rvAdapter?.updateData(viewModel.colList.value!!)

        //if (rvRecipeAdapter == null) {
            rvRecipeAdapter = CollectionAddToPlanAdapter{ itemClicked ->
                val recipe= Recipe(itemClicked.img,itemClicked.title,itemClicked.score,itemClicked.numScore,itemClicked.diff,itemClicked.time,itemClicked.description,
                    itemClicked.profile_img,itemClicked.profile_name,itemClicked.video_url)
                setFragmentResult("request_new", bundleOf("newRecipe" to recipe))
                dismiss()
            }
       // }

        val rvRecipe = view.findViewById<RecyclerView>(R.id.rvChooseRecipe)
        rvRecipe.apply {
            adapter = rvRecipeAdapter
            layoutManager =  GridLayoutManager(context, 2)
        }
        viewModel.colList.value!![0].recipes.let { rvRecipeAdapter!!.updateData(it) }


        val back=view.findViewById<ImageView>(R.id.ivSearchBack)
        back.setOnClickListener(){
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            it.setGravity(Gravity.BOTTOM)
        }
    }

    companion object {
        const val TAG = "SelectCollectionDialogV2"
    }
}