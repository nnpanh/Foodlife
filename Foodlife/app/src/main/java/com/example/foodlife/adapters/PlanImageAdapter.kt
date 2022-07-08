package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.PlanImageModel
import com.example.foodlife.databinding.ItemRecipeImageBinding

class PlanImageAdapter(private val listener: (PlanImageModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<PlanImageModel> = mutableListOf()
    //0 = Breakfast, 1 = Lunch, 2 = Dinner, 3 = Snack

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<PlanImageModel>){
        this.mList= mList.toMutableList()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemRecipeImage: ItemRecipeImageBinding) :
        RecyclerView.ViewHolder(itemRecipeImage.root) {
        fun bindData(_recipe: PlanImageModel) {
            itemRecipeImage.ivDelete.setOnClickListener { listener(_recipe) }
            itemRecipeImage.apply {
                recipe =_recipe
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemRecipeImageBinding.inflate(
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