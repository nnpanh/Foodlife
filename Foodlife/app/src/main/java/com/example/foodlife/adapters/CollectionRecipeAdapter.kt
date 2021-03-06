package com.example.foodlife.adapters

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.AdapterRecipeCollectionBinding
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.databinding.ItemRecipeSearchBinding
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Recipe

class CollectionRecipeAdapter(private val search: MutableList<Recipe>, private val listener: (Recipe) -> Unit) :RecyclerView.Adapter<CollectionRecipeAdapter.ViewHolder>(){
    var searchList: MutableList<Recipe> = mutableListOf()
    private var removeIcons = mutableListOf<ImageView>()
    private var remove = true
    private var adapter = this

    fun updateData(searhList: MutableList<Recipe>){
        this.searchList= searhList
        notifyDataSetChanged()
    }
    init{
        searchList=search
    }

    inner class ViewHolder(private val adapterRecipeCollectionBinding: AdapterRecipeCollectionBinding) :
        RecyclerView.ViewHolder(adapterRecipeCollectionBinding.root){

        fun bindData(_recipe: Recipe) {
            adapterRecipeCollectionBinding.itemCollection.setOnClickListener{listener(_recipe)}
            adapterRecipeCollectionBinding.apply {
                recipe =_recipe
                val connectString = _recipe.time.toString()+" mins"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                adapterRecipeCollectionBinding.colectTime.text=textValue
                adapterRecipeCollectionBinding.collectRate.text=_recipe.score.toString()
                executePendingBindings()
                removeIcons.add(adapterRecipeCollectionBinding.ivRemove)
                adapterRecipeCollectionBinding.ivRemove.setOnClickListener {
                    adapter.searchList.removeAt(adapterPosition)
                    adapter.notifyItemRemoved(adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AdapterRecipeCollectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(searchList[position])
    }

    fun changeStatus() {
        for (item in removeIcons) {
            if (!remove) item.visibility = View.GONE
            else item.visibility = View.VISIBLE
        }
        remove = !remove
    }

}