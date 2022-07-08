package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemCollectionHomeBinding
import com.example.foodlife.models.Recipe

class CollectionHomeAdapter: RecyclerView.Adapter<CollectionHomeAdapter.ViewHolder>() {
    var collectionList: List<Recipe> = emptyList()
    fun updateData(collectionList: List<Recipe>){
        this.collectionList=collectionList

    }
    inner class ViewHolder(private val view: ItemCollectionHomeBinding) :
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
            ItemCollectionHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(collectionList[position])
    }
}