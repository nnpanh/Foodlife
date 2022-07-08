package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemMainCategoryBinding
import com.example.foodlife.models.MainCategoryModel

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {
    var mainList: List<MainCategoryModel> = emptyList()
    fun updateData(main_list: List<MainCategoryModel>){
        this.mainList=main_list

    }
    inner class ViewHolder(private val view: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(view.root){
        fun bindData(_mainCat: MainCategoryModel) {
            view.apply {
                mainCat =_mainCat
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMainCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bindData(mainList[position])
    }
}