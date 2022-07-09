package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemRecipeSearchBinding
import com.example.foodlife.databinding.ItemRecommendedHomeBinding
import com.example.foodlife.models.Recipe

class SearchRecipeAdapter :RecyclerView.Adapter<SearchRecipeAdapter.ViewHolder>() {
    var searchlist: List<Recipe> = emptyList()
    fun updateData(searchlist: List<Recipe>){
        this.searchlist=searchlist
    }

    inner class ViewHolder(private val view: ItemRecipeSearchBinding) :
        RecyclerView.ViewHolder(view.root){
        fun bindData(_recipe: Recipe) {
            view.apply {
                recipe =_recipe
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecipeSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(searchlist[position])
    }

}