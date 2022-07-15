package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentSearchBinding
import com.example.foodlife.databinding.ItemRecipeSearchBinding
import com.example.foodlife.databinding.ItemRecommendedHomeBinding
import com.example.foodlife.models.Collection
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.models.Recipe
import com.example.foodlife.models.RecommendCategoryModel

class SearchRecipeAdapter(private val search: List<Recipe>, private val listener: (Recipe) -> Unit) :RecyclerView.Adapter<SearchRecipeAdapter.ViewHolder>(), Filterable {
    var searchlist: List<Recipe> = emptyList()
    init{
        searchlist=search
    }

    fun updateData(searhList: List<Recipe>){
        this.searchlist= searhList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemRecipeSearchBinding: ItemRecipeSearchBinding) :
        RecyclerView.ViewHolder(itemRecipeSearchBinding.root){

        fun bindData(_recipe: Recipe) {
            itemRecipeSearchBinding.itemSearch.setOnClickListener{listener(_recipe)}
            itemRecipeSearchBinding.apply {
                recipe =_recipe
                val connectString = _recipe.time.toString()+" mins"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemRecipeSearchBinding.tvTime.text=textValue
                itemRecipeSearchBinding.tvRating.text=_recipe.score.toString()
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
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty())
                    searchlist = search
                else {
                    val resultList = ArrayList<Recipe>()
                    for (row in searchlist)
                        if (row.title.lowercase().contains(charSearch.lowercase()))
                            resultList.add(row)
                    searchlist = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = searchlist
                return filterResult
            }
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                searchlist = p1!!.values as List<Recipe>
                notifyDataSetChanged()
            }

        }
    }
}