package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemArDirectionBinding
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.AddRecipeIngredientModel

class AddRecipeDirectionAdapter (private val listener: (AddRecipeDirectionModel) -> Unit) : RecyclerView.Adapter <RecyclerView.ViewHolder>() {

    var mList: MutableList<AddRecipeDirectionModel> = mutableListOf()
    private val GALLERY_REQ_CODE = 1000

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
//            itemDirectionBinding.ivUploadimage.setOnClickListener{listener(_direction)}
/*            itemDirectionBinding.ivUploadimage.setOnClickListener(object : AdapterView.OnItemClickListener,
                View.OnClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }

                override fun onClick(v: View?) {}

            })*/
            itemDirectionBinding.apply {
                direction = _direction
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