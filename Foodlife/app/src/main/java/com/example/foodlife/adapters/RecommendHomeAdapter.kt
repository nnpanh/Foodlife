package com.example.foodlife.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemRecommendCopyBinding
import com.example.foodlife.databinding.ItemRecommendedHomeBinding
import com.example.foodlife.models.Recipe

class RecommendHomeAdapter(private val listener: (Recipe) -> Unit): RecyclerView.Adapter<RecommendHomeAdapter.ViewHolder>() {
    var rec_list: List<Recipe> = emptyList()
    fun updateData(rec_list: List<Recipe>){
        this.rec_list=rec_list

    }
    inner class ViewHolder(private val itemRecommendCopyBinding: ItemRecommendCopyBinding) :
        RecyclerView.ViewHolder(itemRecommendCopyBinding.root){

        fun bindData(_recipe: Recipe) {
            itemRecommendCopyBinding.recHomeItem.setOnClickListener{listener(_recipe)}
            itemRecommendCopyBinding.apply {
                recipe =_recipe
                val connectString = _recipe.time.toString()+" mins"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemRecommendCopyBinding.tvRecTime.text=textValue
                itemRecommendCopyBinding.tvRecStar.text=_recipe.score.toString()
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecommendCopyBinding.inflate(
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