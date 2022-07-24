package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemCollectionHomeV2Binding
import com.example.foodlife.models.Recipe

class CollectionHomeAdapterV2(private val listener: (Recipe) -> Unit,
                              private val deleteListener: (Recipe) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList:MutableList<Recipe> = mutableListOf()
    var deleteMode = false


    fun updateData(collectionList: MutableList<Recipe> = mutableListOf()){
        this.collectionList=collectionList
        notifyDataSetChanged()
    }

    fun updateDeleteMode(deleteMode: Boolean){
        this.deleteMode = deleteMode
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemCollectionHomeBinding: ItemCollectionHomeV2Binding) :
        RecyclerView.ViewHolder(itemCollectionHomeBinding.root) {
        fun bindData(_recipe: Recipe) {
            if (deleteMode) {
                itemCollectionHomeBinding.icClose.visibility = View.VISIBLE
            } else itemCollectionHomeBinding.icClose.visibility = View.GONE
            itemCollectionHomeBinding.collectHomeInfo.setOnClickListener{listener(_recipe)}
            itemCollectionHomeBinding.icClose.setOnClickListener { deleteListener(_recipe) }
            itemCollectionHomeBinding.apply {
                recipe=_recipe
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemCollectionHomeV2Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=collectionList[position]
        (holder as ItemViewHolder).bindData(item)
        holder.itemView.setOnClickListener{

        }
    }
}