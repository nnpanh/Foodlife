package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemRecommendFrameBinding
import com.example.foodlife.databinding.ItemRecommendedHomeBinding
import com.example.foodlife.models.Recipe

class RecommendFrameAdapter(private val listener: (Recipe) -> Unit,
private val listenerCollection: (Recipe) ->Unit): RecyclerView.Adapter<RecommendFrameAdapter.ViewHolder>() {
    var rec_list: List<Recipe> = emptyList()
    fun updateData(rec_list: List<Recipe>){
        this.rec_list=rec_list.toMutableList()
        notifyDataSetChanged()

    }
    inner class ViewHolder(private val itemRecommendFrameBinding: ItemRecommendFrameBinding) :
        RecyclerView.ViewHolder(itemRecommendFrameBinding.root){
        fun bindData(_recipe: Recipe) {
            itemRecommendFrameBinding.recFrameImg.setOnClickListener{listener(_recipe)}
            itemRecommendFrameBinding.recFrameSave.setOnClickListener{listenerCollection(_recipe)}
            itemRecommendFrameBinding.apply {
                recipe =_recipe

                val connectString = _recipe.time.toString()+" mins"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemRecommendFrameBinding.tvRecTime.text=textValue
                itemRecommendFrameBinding.tvRecRating.text=_recipe.score.toString()
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecommendFrameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rec_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(rec_list[position])

    }
}