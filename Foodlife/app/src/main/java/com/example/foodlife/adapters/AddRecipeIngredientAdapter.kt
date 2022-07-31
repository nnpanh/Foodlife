package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.databinding.ItemArIngredientBinding
import com.example.foodlife.models.AddRecipeIngredientModel


class AddRecipeIngredientAdapter(private var mContext: Context,
                                 private val listener: (AddRecipeIngredientModel) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mList: MutableList<AddRecipeIngredientModel> = mutableListOf()
    private var isSpinnerInitial = true

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: MutableList<AddRecipeIngredientModel>){
        this.mList= mList
        notifyDataSetChanged()
    }

    fun addData(item: AddRecipeIngredientModel){
        this.mList.add(item)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val itemIngredientBinding: ItemArIngredientBinding) :
        RecyclerView.ViewHolder(itemIngredientBinding.root) {
        fun bindData(_ingredient: AddRecipeIngredientModel) {
            val arraySpinner = arrayOf("gr", "kg", "ml", "l", "cup", "tbsp", "tsp", "oz", "lb")
            val dropdownAdapter = ArrayAdapter(mContext, R.layout.item_spinner, arraySpinner)
            dropdownAdapter.setDropDownViewResource(R.layout.item_spinner)
            itemIngredientBinding.measurementDropdown.adapter = dropdownAdapter

            itemIngredientBinding.measurementDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.e("chosen", id.toString())
                    Log.e("position", adapterPosition.toString())
                    mList[adapterPosition].measure = itemIngredientBinding.measurementDropdown.selectedItem.toString()
                    mList[adapterPosition].selectedPosition = itemIngredientBinding.measurementDropdown.selectedItemPosition
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {}
            }
            itemIngredientBinding.ivDelete.setOnClickListener{listener(_ingredient)}
            itemIngredientBinding.measurementDropdown.setSelection(_ingredient.selectedPosition)
            itemIngredientBinding.apply {
                ingredient = _ingredient
                executePendingBindings()
            }
            itemIngredientBinding.edIngredient.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    if(containsIllegalCharacters(itemIngredientBinding.edIngredient.text.toString())){
                        Toast.makeText(mContext, "Emoji is not allowed", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun containsIllegalCharacters(inputString: String): Boolean {
        val nameLength = inputString.length
        for (i in 0 until nameLength) {
            /*val hs = inputString[i]
            if (0xd800 <= hs.code && hs.code <= 0xdbff) {
                val ls = inputString[i + 1]
                val uc = (hs.code - 0xd800) * 0x400 + (ls.code - 0xdc00) + 0x10000
                if (0x1d000 <= uc && uc <= 0x1f77f) {
                    return true
                }
            } else if (Character.isHighSurrogate(hs)) {
                val ls = inputString[i + 1]
                if (ls.code == 0x20e3) {
                    return true
                }
            } else {
                // non surrogate
                if (0x2100 <= hs.code && hs.code <= 0x27ff) {
                    return true
                } else if (0x2B05 <= hs.code && hs.code <= 0x2b07) {
                    return true
                } else if (0x2934 <= hs.code && hs.code <= 0x2935) {
                    return true
                } else if (0x3297 <= hs.code && hs.code <= 0x3299) {
                    return true
                } else if (hs.code == 0xa9 || hs.code == 0xae || hs.code == 0x303d || hs.code == 0x3030 || hs.code == 0x2b55 || hs.code == 0x2b1c || hs.code == 0x2b1b || hs.code == 0x2b50) {
                    return true
                }
            }*/
            val t = inputString[i]
            if(t.code in 0x0021..0x0027)
                return true
            if(t.code == 0x002a || t.code == 0x2b)
                return true
            if(t.code in 0x003a..0x0040)
                return true
            if(t.code in 0x005b..0x0060)
                return true
            if(t.code > 0x007b)
                return true
            return !(Character.compare(t,'a') >= 0 && Character.compare(t,'z') <= 0 ) && !(t >= 'A' && Character.compare(t,'Z') <= 0 ) && !(Character.compare(t,'1') >= 0 && Character.compare(t,'9') <= 0)
        }
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemArIngredientBinding.inflate(
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