package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemCollectionHomeBinding
import com.example.foodlife.databinding.ItemIngredientsBinding
import com.example.foodlife.databinding.ItemRecipeSearchBinding
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.Recipe

class CollectionAddToPlanAdapter(private val listener: (Recipe) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var collectionList:MutableList<Recipe> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(collectionList: MutableList<Recipe> = mutableListOf()){
        this.collectionList=collectionList
        notifyDataSetChanged()
    }
    inner class ItemViewHolder(private val itemRecipeSearchBinding: ItemRecipeSearchBinding) :
        RecyclerView.ViewHolder(itemRecipeSearchBinding.root) {
        fun bindData(_recipe: Recipe) {
            itemRecipeSearchBinding.itemSearch.setOnClickListener{listener(_recipe)}
            itemRecipeSearchBinding.apply {
                recipe=_recipe
                val connectString = _recipe.time.toString()+" mins"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemRecipeSearchBinding.tvTime.text=textValue
                itemRecipeSearchBinding.tvRating.text=_recipe.score.toString()
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemRecipeSearchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CollectionAddToPlanAdapter.ItemViewHolder).bindData(collectionList[position])
        /*var item=collectionList[position]
        (holder as ItemViewHolder).bindData(item)
        holder.itemView.setOnClickListener{
            listener(item)
        }*/
    }
}