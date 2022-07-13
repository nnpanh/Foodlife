package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemCollectionHomeBinding
import com.example.foodlife.databinding.ItemIngredientsBinding
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.Recipe

class CollectionHomeAdapter(private val listener: (Recipe) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList:MutableList<Recipe> = mutableListOf()

    fun updateData(collectionList: MutableList<Recipe> = mutableListOf()){
        this.collectionList=collectionList

    }
    inner class ItemViewHolder(private val itemCollectionHomeBinding: ItemCollectionHomeBinding) :
        RecyclerView.ViewHolder(itemCollectionHomeBinding.root) {
        fun bindData(_recipe: Recipe) {
            itemCollectionHomeBinding.collectHomeInfo.setOnClickListener{listener(_recipe)}
            itemCollectionHomeBinding.apply {
                recipe=_recipe
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemCollectionHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item=collectionList[position]
        (holder as ItemViewHolder).bindData(item)
        holder.itemView.setOnClickListener{

        }
    }
}