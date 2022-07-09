package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemRecommendCategoryBinding
import com.example.foodlife.models.RecommendCategoryModel
import com.example.foodlife.view_models.HomeViewModel


class RecommendCategoryAdapter(private val listener: (HomeViewModel) -> Unit):RecyclerView.Adapter<RecommendCategoryAdapter.ViewHolder>() {
    var catList: List<RecommendCategoryModel> = emptyList()
    fun updateData(catList: List<RecommendCategoryModel>){
        this.catList=catList

    }
    //var gPosition: Int=-1

    inner class ViewHolder(private val view: ItemRecommendCategoryBinding) :

        RecyclerView.ViewHolder(view.root){
        //var selPosition:Int=-1
        fun bindData(_mainCat: RecommendCategoryModel) {
            view.apply {
                mainCat =_mainCat
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
           ItemRecommendCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = catList[position]

        (holder as ViewHolder).bindData(item)
        holder.itemView.setOnClickListener{
            println(position)
            /*if (position==gPosition){
                val color = ContextCompat.getColor(holder.itemView.context, R.color.white)
                it.findViewById<TextView>(R.id.tvRecCatLine).setBackgroundColor(color)
            }
            else{
                val color = ContextCompat.getColor(holder.itemView.context, R.color.secondary_100)
                it.findViewById<TextView>(R.id.tvRecCatLine).setBackgroundColor(color)
            }
        holder.selPosition=position*/
        }
    }
}