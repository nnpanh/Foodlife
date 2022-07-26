package com.example.foodlife.adapters


import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.databinding.ItemAddCollectionToPlanBinding
import com.example.foodlife.databinding.ItemRecommendCategoryBinding
import com.example.foodlife.models.Collection
import com.example.foodlife.models.RecommendCategoryModel
import com.example.foodlife.view_models.CollectionViewModel
import com.example.foodlife.view_models.HomeViewModel


class SelectCollectionAdapterV2(private val listener: (Collection) -> Unit):RecyclerView.Adapter<SelectCollectionAdapterV2.ViewHolder>() {
    var catList: List<Collection> = emptyList()


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(catList: List<Collection>){
        this.catList=catList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemAddCollectionToPlanBinding: ItemAddCollectionToPlanBinding) :

        RecyclerView.ViewHolder(itemAddCollectionToPlanBinding.root){
        fun bindData(_collection: Collection) {
            itemAddCollectionToPlanBinding.apply {
                collection=_collection
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
           ItemAddCollectionToPlanBinding.inflate(
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
        if (item.img != "")
            holder.itemView.findViewById<com.google.android.material.imageview.ShapeableImageView>(com.example.foodlife.R.id.ivDish).setImageURI(Uri.parse(item.img))

        if (item.oldImg!=null)
            holder.itemView.findViewById<com.google.android.material.imageview.ShapeableImageView>(com.example.foodlife.R.id.ivDish).setImageResource(item.oldImg!!)

        if (selectedPosition == position) {
            holder.itemView.isSelected = true //using selector drawable
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvStatusCircle).setBackgroundResource(R.drawable.circle_shape)
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvName).setTextColor(
                ContextCompat.getColor(holder.itemView.getContext(),R.color.primary_100))

        } else {
            holder.itemView.isSelected = false
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvStatusCircle).setBackgroundResource(R.drawable.circle_shape_normal)
            holder.itemView.findViewById<TextView>(com.example.foodlife.R.id.tvName).setTextColor(
                ContextCompat.getColor(holder.itemView.getContext(),R.color.primary_40))
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