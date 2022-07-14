package com.example.foodlife.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.Collection
import com.example.foodlife.models.PlanItemModel


class CollectionAdapter (private val collection: List<Collection>): RecyclerView.Adapter<CollectionAdapter.ViewHolder>(), Filterable {
    private var context: Context? = null
    private var filterCollection: List<Collection>
    var onItemClick: ((Collection) -> Unit)? = null

    init {
        filterCollection = collection
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(mList: List<Collection>){
        this.filterCollection= mList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val IVCol = listItemView.findViewById<ImageFilterView>(R.id.IVCol)!!
        val TVColName = listItemView.findViewById<TextView>(R.id.TVColName)!!
        val TVColQuantity = listItemView.findViewById<TextView>(R.id.TVColQuantity)!!
        init {
            listItemView.setOnClickListener { onItemClick?.invoke(filterCollection[adapterPosition]) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.adapter_collection, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filterCollection[position]
        if (item.img != "")
            holder.IVCol.setImageURI(Uri.parse(item.img))
        holder.IVCol.contrast = 0.8F
        /**
         * Set image
         */
        if (item.oldImg!=null) holder.IVCol.setBackgroundResource(item.oldImg!!)
        holder.TVColName.text = item.title
        holder.TVColQuantity.text = item.quantity.toString() + " Recipes"

    }

    override fun getItemCount(): Int {
        return filterCollection.size
    }
    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty())
                    filterCollection = collection
                else {
                    val resultList = ArrayList<Collection>()
                    for (row in collection)
                        if (row.title.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    filterCollection = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = filterCollection
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterCollection = p1!!.values as List<Collection>
                notifyDataSetChanged()
            }

        }
    }
}