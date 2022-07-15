package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.databinding.ItemArDirectionBinding
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.AddRecipeIngredientModel

class AddRecipeDirectionAdapter (private val listener: (AddRecipeDirectionModel) -> Unit) : RecyclerView.Adapter <RecyclerView.ViewHolder>() {

    var mList: MutableList<AddRecipeDirectionModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<AddRecipeDirectionModel>){
        this.mList= mList
        notifyDataSetChanged()
    }

    fun addData(item: AddRecipeDirectionModel){
        this.mList.add(item)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemDirectionBinding: ItemArDirectionBinding) :
        RecyclerView.ViewHolder(itemDirectionBinding.root) {
        fun bindData(_direction: AddRecipeDirectionModel) {
            itemDirectionBinding.ivDelete.setOnClickListener{listener(_direction)}
            itemDirectionBinding.apply {
                direction = _direction
                val step = _direction.num
                itemDirectionBinding.txtStep.setText("Step ${position + 1}")
//                val name = _ingredient.name
//                val quantity = _ingredient.quantity
//                itemIngredientBinding.edIngredient.setHint(name)
//                itemIngredientBinding.etIngredientQuantity.setHint(quantity.toString())
                executePendingBindings()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemArDirectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        (holder as AddRecipeDirectionAdapter.ItemViewHolder).bindData(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}