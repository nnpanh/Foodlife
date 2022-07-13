package com.example.foodlife.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.databinding.ItemRecommendCategoryBinding
import com.example.foodlife.models.RecommendCategoryModel
import com.example.foodlife.view_models.HomeViewModel


class RecommendCategoryAdapter(private val listener: (RecommendCategoryModel) -> Unit):RecyclerView.Adapter<RecommendCategoryAdapter.ViewHolder>() {
    var catList: List<RecommendCategoryModel> = emptyList()

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    fun updateData(catList: List<RecommendCategoryModel>){
        this.catList=catList

    }

    inner class ViewHolder(private val itemRecommendCategoryBinding: ItemRecommendCategoryBinding) :

        RecyclerView.ViewHolder(itemRecommendCategoryBinding.root){
        fun bindData(_mainCat: RecommendCategoryModel) {
            //itemRecommendCategoryBinding.tvRecCatName.setOnClickListener { listener(_mainCat) }
            itemRecommendCategoryBinding.apply {
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


    private var selectedPosition = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = catList[position]
        (holder ).bindData(item)

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvRecCatLine).setBackgroundColor(
                ContextCompat.getColor(holder.itemView.getContext(),R.color.secondary_100))

        } else {
            holder.itemView.isSelected = false
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvRecCatLine).setBackgroundColor(
                ContextCompat.getColor(holder.itemView.getContext(),R.color.white))
        }

        holder.itemView.setOnClickListener {
            listener(item)
            if (selectedPosition >= 0) notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            //holder.getAdapterPosition();

        }

    }
}