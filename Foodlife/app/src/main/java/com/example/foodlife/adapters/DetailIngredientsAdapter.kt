package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemDetailIngredientsBinding
import com.example.foodlife.models.DetailIngredients

class DetailIngredientsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<DetailIngredients> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<DetailIngredients>){
        this.mList= mList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemDetailIngredientsBinding: ItemDetailIngredientsBinding) :
        RecyclerView.ViewHolder(itemDetailIngredientsBinding.root) {
        fun bindData(_detailIngredients: DetailIngredients) {
            itemDetailIngredientsBinding.apply {
                detailIngredients = _detailIngredients
                val connectString = "<b>${_detailIngredients.bold}</b> /n ${_detailIngredients.regular}"
                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                itemDetailIngredientsBinding.tvDishTitle.text = textValue
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailIngredientsAdapter.ItemViewHolder {
        val binding =
            ItemDetailIngredientsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as DetailIngredientsAdapter.ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}