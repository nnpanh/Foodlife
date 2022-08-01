package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemDetailDirectionsBinding
import com.example.foodlife.models.DetailDirections

class DetailDirectionsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<DetailDirections> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<DetailDirections>){
        this.mList= mList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemDetailDirectionsBinding: ItemDetailDirectionsBinding) :
        RecyclerView.ViewHolder(itemDetailDirectionsBinding.root) {
        fun bindData(_detailDirections: DetailDirections) {
            itemDetailDirectionsBinding.imageView2.setOnClickListener { iv ->
                if (_detailDirections.isSelected)
                    iv.setBackgroundResource(0)
                else iv.setBackgroundResource(R.drawable.circle_fill)
                //Reverse
                _detailDirections.isSelected = !_detailDirections.isSelected
            }

            if(_detailDirections.imgUri != null){
                itemDetailDirectionsBinding.ivDir.setImageURI(_detailDirections.imgUri)
            }else itemDetailDirectionsBinding.ivDir.setImageResource(_detailDirections.img)
            itemDetailDirectionsBinding.apply {
                detailDirections = _detailDirections
//                val connectString = "<b>${_detailDirections.bold}</b> <br/> ${_detailDirections.regular}"
//                val textValue = HtmlCompat.fromHtml(connectString, HtmlCompat.FROM_HTML_MODE_LEGACY)
//                itemDetailDirectionsBinding.tvDir.text = textValue
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailDirectionsAdapter.ItemViewHolder {
        val binding =
            ItemDetailDirectionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as DetailDirectionsAdapter.ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}