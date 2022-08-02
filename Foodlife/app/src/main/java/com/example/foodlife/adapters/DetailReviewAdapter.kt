package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemDetailReviewBinding
import com.example.foodlife.models.DetailReview


class DetailReviewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<DetailReview> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<DetailReview>){
        this.mList= mList
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemDetailReviewBinding: ItemDetailReviewBinding) :
        RecyclerView.ViewHolder(itemDetailReviewBinding.root) {
        fun bindData(_detailReview: DetailReview) {

            itemDetailReviewBinding.apply {
                detailReview = _detailReview
                val tmp = _detailReview.showcase
                if (tmp != null){
                    itemDetailReviewBinding.ivShowcase.setImageResource(tmp)
                    itemDetailReviewBinding.ivShowcase.layoutParams.height = 600
                }
                executePendingBindings()
            }
        }
    }
    fun getI(): Int {
        var imageCount = 0
        for (item in mList){
            if (item.showcase!=null)
                imageCount=imageCount+1
        }
        return imageCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailReviewAdapter.ItemViewHolder {
        val binding =
            ItemDetailReviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as DetailReviewAdapter.ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}