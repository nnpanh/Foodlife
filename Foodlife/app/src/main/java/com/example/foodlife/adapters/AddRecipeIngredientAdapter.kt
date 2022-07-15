package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeIngredientModel


class AddRecipeIngredientAdapter(private val listener: (AddRecipeIngredientModel) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<AddRecipeIngredientModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<AddRecipeIngredientModel>){
        this.mList= mList
        notifyDataSetChanged()
    }

    fun addData(item: AddRecipeIngredientModel){
        this.mList.add(item)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemIngredientBinding: ItemArIngredientBinding) :
        RecyclerView.ViewHolder(itemIngredientBinding.root) {
        fun bindData(_ingredient: AddRecipeIngredientModel) {
            itemIngredientBinding.ivDelete.setOnClickListener{listener(_ingredient)}
            itemIngredientBinding.apply {
                ingredient = _ingredient
                val name = _ingredient.name
                val quantity = _ingredient.quantity
                itemIngredientBinding.edIngredient.setHint(name)
                itemIngredientBinding.etIngredientQuantity.setHint(quantity.toString())

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