package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeIngredientModel


class AddRecipeIngredientAdapter(private var mContext: Context,
                                 private val listener: (AddRecipeIngredientModel) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<AddRecipeIngredientModel> = mutableListOf()
    private var isSpinnerInitial = true

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<AddRecipeIngredientModel>){
        this.mList= mList
        notifyDataSetChanged()
    }

    fun addData(item: AddRecipeIngredientModel){
        this.mList.add(item)
        notifyDataSetChanged()
    }

    fun getList(): MutableList<AddRecipeIngredientModel> {
        return this.mList

    }

    inner class ItemViewHolder(private val itemIngredientBinding: ItemArIngredientBinding) :
        RecyclerView.ViewHolder(itemIngredientBinding.root) {
        fun bindData(_ingredient: AddRecipeIngredientModel) {
            val arraySpinner = arrayOf("gr", "kg", "ml", "l", "cup", "tbsp", "tsp", "oz", "lb")
            val dropdownAdapter = ArrayAdapter(mContext, R.layout.item_spinner, arraySpinner)
            dropdownAdapter.setDropDownViewResource(R.layout.item_spinner)
            //ArrayAdapter(requireActivity(),com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
            itemIngredientBinding.measurementDropdown.adapter = dropdownAdapter

            itemIngredientBinding.measurementDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isSpinnerInitial)
                        isSpinnerInitial = false
                    else{
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {}

            }
            itemIngredientBinding.ivDelete.setOnClickListener{listener(_ingredient)}
            itemIngredientBinding.apply {
                ingredient = _ingredient
//                val name = _ingredient.name
//                val quantity = _ingredient.quantity
//                itemIngredientBinding.edIngredient.setHint(name)
//                itemIngredientBinding.etIngredientQuantity.setHint(quantity.toString())
                executePendingBindings()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemArIngredientBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}