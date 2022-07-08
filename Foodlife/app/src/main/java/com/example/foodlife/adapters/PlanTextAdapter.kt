package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.databinding.ItemRecipeTextBinding

class PlanTextAdapter(_type: Int,  private val listener: (PlanItemModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<PlanItemModel> = mutableListOf()

    val type: Int = _type

    //0 = Breakfast, 1 = Lunch, 2 = Dinner, 3 = Snack

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<PlanItemModel>){
        this.mList= mList.toMutableList()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemRecipeText: ItemRecipeTextBinding) :
        RecyclerView.ViewHolder(itemRecipeText.root) {
        fun bindData(_recipe: PlanItemModel) {
            itemRecipeText.ivDelete.setOnClickListener { listener(_recipe) }
            itemRecipeText.apply {
                recipe =_recipe
                when (type){
                    0 -> {
                        layoutItem.setBackgroundResource(R.drawable.bgr_round_blue)
                        return
                    }
                    1 -> {
                        layoutItem.setBackgroundResource(R.drawable.bgr_round_green)
                        return
                    }
                    2 -> {
                        layoutItem.setBackgroundResource(R.drawable.bgr_round_yellow)
                        return
                    }
                    else -> {
                        layoutItem.setBackgroundResource(R.drawable.bgr_round_red)
                    }
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemRecipeTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as ItemViewHolder).bindData(item)
//        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}