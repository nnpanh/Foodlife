package com.example.foodlife.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemRecipeSearchBinding
import com.example.foodlife.models.Recipe

class ChoiceRecipeAdapter (private val listener: (Recipe) -> Unit) :
    RecyclerView.Adapter<ChoiceRecipeAdapter.ViewHolder>() {
    var searchlist: List<Recipe> = emptyList()

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
}