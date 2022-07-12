package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.BottomDialogOption
import com.example.foodlife.databinding.ItemAddToPlanBinding

class AddToPlanAdapter(private val listener: (BottomDialogOption) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<BottomDialogOption> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<BottomDialogOption>){
        this.mList= mList.toMutableList()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(private val itemBottomDialog: ItemAddToPlanBinding) :
        RecyclerView.ViewHolder(itemBottomDialog.root) {
        fun bindData(_option: BottomDialogOption) {
            itemBottomDialog.layoutItem.setOnClickListener {
                listener(_option)
            }
            itemBottomDialog.option = _option
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemAddToPlanBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}