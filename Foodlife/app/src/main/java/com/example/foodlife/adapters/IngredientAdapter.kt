package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemIngredientsBinding
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.AddRecipeIngredient

class IngredientAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<Ingredient> = mutableListOf()
    //0 = Breakfast, 1 = Lunch, 2 = Dinner, 3 = Snack

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<Ingredient>){
        this.mList= mList
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemIngredientBinding: ItemIngredientsBinding) :
        RecyclerView.ViewHolder(itemIngredientBinding.root) {
        fun bindData(_ingredient: Ingredient) {
            itemIngredientBinding.apply {
                ingredient = _ingredient
                val connectString = "<b>${_ingredient.bold}</b> ${_ingredient.regular}"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemIngredientBinding.tvDishTitle.text = textValue
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemIngredientsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as ItemViewHolder).bindData(item)
        holder.itemView.setOnClickListener {
                if (item.selected) {
                    item.selected = false
                    it.findViewById<ImageView>(R.id.ivCheckbox).setImageResource(R.drawable.ic_checkbox)
                } else {
                    item.selected = true
                    it.findViewById<ImageView>(R.id.ivCheckbox).setImageResource(R.drawable.ic_checkbox_selected)
                }
            }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}