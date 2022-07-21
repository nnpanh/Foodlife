package com.example.foodlife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.databinding.ItemMainCategoryBinding
import com.example.foodlife.models.MainCategoryModel
import com.example.foodlife.models.RecommendCategoryModel

class MainCategoryAdapter(private val listener: (MainCategoryModel) -> Unit): RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {
    var mainList: List<MainCategoryModel> = emptyList()

    private var _binding: ItemMainCategoryBinding? = null
    private val binding get() = _binding!!

    fun updateData(main_list: List<MainCategoryModel>){
        this.mainList=main_list

    }
    inner class ViewHolder(private val itemMainCategoryBinding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(itemMainCategoryBinding.root){


        fun bindData(_mainCat: MainCategoryModel) {
            itemMainCategoryBinding.catImg.setOnClickListener { listener(_mainCat) }
            itemMainCategoryBinding.apply {
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
        val item = mainList[position]
        (holder ).bindData(item)

        /*holder.itemView.setOnClickListener {
            listener(item)

        }*/
    }
}