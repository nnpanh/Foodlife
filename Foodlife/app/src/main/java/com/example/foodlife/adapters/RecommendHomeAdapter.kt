package com.example.foodlife.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemRecommendedHomeBinding
import com.example.foodlife.models.Recipe

class RecommendHomeAdapter(private val listener: (Recipe) -> Unit): RecyclerView.Adapter<RecommendHomeAdapter.ViewHolder>() {
    var rec_list: List<Recipe> = emptyList()
    fun updateData(rec_list: List<Recipe>){
        this.rec_list=rec_list

    }
    inner class ViewHolder(private val itemRecommendedHomeBinding: ItemRecommendedHomeBinding) :
        RecyclerView.ViewHolder(itemRecommendedHomeBinding.root){

        fun bindData(_recipe: Recipe) {
            itemRecommendedHomeBinding.recHomeItem.setOnClickListener{listener(_recipe)}
            itemRecommendedHomeBinding.apply {
                recipe =_recipe
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecommendedHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rec_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(rec_list[position])
    }
}