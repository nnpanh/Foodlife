package com.example.foodlife.adapters

import android.content.Context
import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.models.Collection


class CollectionAdapter (private val collection: List<Collection>): RecyclerView.Adapter<CollectionAdapter.ViewHolder>(), Filterable {
    private var context: Context? = null
    private var filterCollection: List<Collection>
    private var onItemClick: ((Collection) -> Unit)? = null

    init {
        filterCollection = collection
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val IVCol = listItemView.findViewById<ImageView>(R.id.IVCol)!!
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
        holder.IVCol.setImageBitmap(
            changeBitmapContrastBrightness(
                BitmapFactory.decodeResource(
                    context!!.resources,
                    R.drawable.img_collection
                ),0.75F , 1F
            ))
        holder.TVColName.text = filterCollection[position].title
        holder.TVColQuantity.text = filterCollection[position].quantity.toString() + " Recipes"

    }

    override fun getItemCount(): Int {
        return filterCollection.size
    }

    private fun changeBitmapContrastBrightness(bmp: Bitmap, contrast: Float, brightness: Float): Bitmap? {
        val cm = ColorMatrix(
            floatArrayOf(
                contrast,
                0f,
                0f,
                0f,
                brightness,
                0f,
                contrast,
                0f,
                0f,
                brightness,
                0f,
                0f,
                contrast,
                0f,
                brightness,
                0f,
                0f,
                0f,
                1f,
                0f
            )
        )
        val ret = Bitmap.createBitmap(bmp.width, bmp.height, bmp.config)
        val canvas = Canvas(ret)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(cm)
        canvas.drawBitmap(bmp, 0f, 0f, paint)
        return ret
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