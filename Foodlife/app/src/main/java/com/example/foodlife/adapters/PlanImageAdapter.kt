package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.databinding.ItemRecipeImageBinding

class PlanImageAdapter(private val listener: (PlanItemModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<PlanItemModel> = mutableListOf()
    //0 = Breakfast, 1 = Lunch, 2 = Dinner, 3 = Snack

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<PlanItemModel>){
        this.mList= mList.toMutableList()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemRecipeImage: ItemRecipeImageBinding) :
        RecyclerView.ViewHolder(itemRecipeImage.root) {
        fun bindData(_recipe: PlanItemModel) {
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