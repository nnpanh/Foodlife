package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.PlanTextModel
import com.example.foodlife.databinding.ItemRecipeTextBinding

class PlanTextAdapter(_type: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: List<PlanTextModel> = emptyList()
    val type: Int = _type
    //0 = Breakfast, 1 = Lunch, 2 = Dinner, 3 = Snack

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<PlanTextModel>){
        this.mList=mList
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemRecipeText: ItemRecipeTextBinding) :
        RecyclerView.ViewHolder(itemRecipeText.root), View.OnClickListener {
        fun bindData(_recipe: PlanTextModel) {
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

        override fun onClick(v: View?) {
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
        (holder as ItemViewHolder).bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}