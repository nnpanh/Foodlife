package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeIngredient

class AddRecipeIngredientAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<AddRecipeIngredient> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<AddRecipeIngredient>){
        this.mList= mList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemIngredientBinding: ItemArIngredientBinding) :
        RecyclerView.ViewHolder(itemIngredientBinding.root) {
        fun bindData(_ingredient: AddRecipeIngredient) {
            itemIngredientBinding.apply {
                ingredient = _ingredient
                val name = _ingredient.name
                val quantity = _ingredient.quantity
                itemIngredientBinding.edIngredient.setText(name)
                itemIngredientBinding.etIngredientQuantity.setText(quantity.toString())
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