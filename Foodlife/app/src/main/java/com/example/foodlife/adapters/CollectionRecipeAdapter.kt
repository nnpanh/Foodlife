package com.example.foodlife.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.Recipe

class CollectionRecipeAdapter(private val recipe: MutableList<Recipe>): RecyclerView.Adapter<CollectionRecipeAdapter.ViewHolder>() {
    var onItemClick: ((Recipe) -> Unit)? = null
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        private var adapter: CollectionRecipeAdapter? = null
        fun linkAdapter(collectionRecipeAdapter: CollectionRecipeAdapter): ViewHolder {
            this.adapter = collectionRecipeAdapter
            return this
        }
        val IVImg = listItemView.findViewById<ImageView>(R.id.IVColRImg)
        val TVTitle = listItemView.findViewById<TextView>(R.id.TVColRTitle)
        val TVTime = listItemView.findViewById<TextView>(R.id.TVColRTime)
        val TVDiff = listItemView.findViewById<TextView>(R.id.TVColRDiff)
        val TVScore = listItemView.findViewById<TextView>(R.id.TVColRScore)
        val IVRemove = listItemView.findViewById<ImageView>(R.id.IVColRemove)
        init {
            listItemView.setOnClickListener { onItemClick?.invoke(recipe[adapterPosition]) }
            IVRemove.setOnClickListener {
                adapter!!.recipe.removeAt(adapterPosition)
                adapter!!.notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.adapter_recipe_collection, parent, false)
        return ViewHolder(contactView).linkAdapter(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.TVTitle.text = recipe[position].title
        holder.TVTime.text = recipe[position].time.toString() + " mins"
        holder.TVDiff.text = recipe[position].diff
        holder.TVScore.text = recipe[position].score.toString()
        if (recipe[position].img != "")
            holder.IVImg.setImageURI(Uri.parse(recipe[position].img))
    }

    override fun getItemCount(): Int {
        return recipe.size
    }
}