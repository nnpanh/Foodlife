package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemArDirectionBinding
import com.example.foodlife.models.AddRecipeDirectionModel

class AddRecipeDirectionAdapter (private val pickListener: (AddRecipeDirectionModel) -> Unit,
                                 private val deleteListener: (AddRecipeDirectionModel) -> Unit) : RecyclerView.Adapter <RecyclerView.ViewHolder>() {

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
            itemDirectionBinding.ivUploadimage.setOnClickListener{pickListener(_direction)}
            itemDirectionBinding.ivDelete.setOnClickListener{deleteListener(_direction)}
            itemDirectionBinding.ivEditimage.setOnClickListener{pickListener(_direction)}
            itemDirectionBinding.ivDeleteimage.setOnClickListener(View.OnClickListener { v ->
                itemDirectionBinding.ivImagePlaceholder.setImageResource(R.drawable.image_placeholder)
                itemDirectionBinding.ivUploadimage.visibility = View.VISIBLE
                itemDirectionBinding.tvUploadTxt.visibility = View.VISIBLE
                itemDirectionBinding.ivEditimage.visibility = View.GONE
                itemDirectionBinding.ivDeleteimage.visibility = View.GONE
                _direction.imageURI = null
            })
            itemDirectionBinding.txtStep.setText("Step ${position + 1}")
            if(_direction.imageURI!= null){
                itemDirectionBinding.ivUploadimage.visibility = View.GONE
                itemDirectionBinding.tvUploadTxt.visibility = View.GONE
                itemDirectionBinding.ivEditimage.visibility = View.VISIBLE
                itemDirectionBinding.ivDeleteimage.visibility = View.VISIBLE
            }
            itemDirectionBinding.ivImagePlaceholder.setImageURI(_direction.imageURI)

            itemDirectionBinding.apply {
                direction = _direction
//                itemDirectionBinding.txtStep.setText("Step ${position + 1}")
//                itemDirectionBinding.ivImagePlaceholder.setImageURI(_direction.imageURI)
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