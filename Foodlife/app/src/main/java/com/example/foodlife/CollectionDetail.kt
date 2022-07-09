package com.example.foodlife

import android.graphics.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.adapters.CollectionRecipeAdapter
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Recipe

class CollectionDetail : AppCompatActivity() {
    private var isSpinnerInitial = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_detail)
        val collection = intent.getSerializableExtra("Collection") as Collection
        val TVTitle = findViewById<TextView>(R.id.TVColDTitle)
        val TVQuantity = findViewById<TextView>(R.id.TVColDQuantity)
        val IVBanner = findViewById<ImageView>(R.id.IVColBanner)
        TVTitle.text = collection.title
        TVQuantity.text = collection.quantity.toString() + " Recipes"
        if (collection.img != "")
            IVBanner.setImageURI(Uri.parse(collection.img))
        else
            IVBanner.setImageBitmap(
                changeBitmapContrastBrightness(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.img_collection
                    ),0.8F , 1F
                ))
        val arraySpinner = arrayOf("Alphabetical", "Latest")
        val filterAdapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
        val SPFilter = findViewById<Spinner>(R.id.SPDFilter)
        SPFilter.adapter = filterAdapter

        val IVBack = findViewById<ImageView>(R.id.IVColBack)
        IVBack.setOnClickListener {
            finish()
        }
        val RVRecipe = findViewById<RecyclerView>(R.id.RVColD)
        var recipeArray = arrayListOf(Recipe(R.drawable.img_collectionrecipe, "Roasted Pork", "3.5", "Medium", "30 mins","",0,""), Recipe(R.drawable.img_collectionrecipe, "Roasted Beef", "3.5", "Medium", "30 mins","",0,""), Recipe(R.drawable.img_collectionrecipe, "Roasted Chicken","3.5", "Medium", "30 mins","",0,""))
        val recipeAdapter = CollectionRecipeAdapter(recipeArray)
        RVRecipe.layoutManager = GridLayoutManager(this, 2)
        RVRecipe.adapter = recipeAdapter
        val IVGrid = findViewById<ImageView>(R.id.IVColGrid)
        val IVList = findViewById<ImageView>(R.id.IVColList)
        IVGrid.setOnClickListener {
            RVRecipe.layoutManager = GridLayoutManager(this, 2)
        }
        IVList.setOnClickListener {
            RVRecipe.layoutManager = LinearLayoutManager(this)
        }
        SPFilter.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(isSpinnerInitial)
                    isSpinnerInitial = false
                else {
                    recipeArray.shuffle()
                    RVRecipe.adapter = recipeAdapter
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        recipeAdapter.onItemClick = { recipe ->
            //val intent = Intent()
        }
        supportActionBar!!.hide()
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
}