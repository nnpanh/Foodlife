package com.example.foodlife.fragments

import android.graphics.*
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.MainActivity
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionRecipeAdapter
import com.example.foodlife.databinding.FragmentCollectionDetailBinding
import com.example.foodlife.models.Collection


class CollectionDetail : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentCollectionDetailBinding? = null
    private val binding get() = _binding!!
    private var isSpinnerInitial = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectionDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        //val collection = intent.getSerializableExtra("Collection") as Collection
        val collection = arguments?.getSerializable("Collection") as Collection
        val TVTitle = binding.TVColDTitle
        val TVQuantity = binding.TVColDQuantity
        val IVBanner = binding.IVColBanner
        TVTitle.text = collection.title
        TVQuantity.text = collection.quantity.toString() + " Recipes"
        if (collection.img != "")
            IVBanner.setImageURI(Uri.parse(collection.img))
        else
            IVBanner.setImageBitmap(
                changeBitmapContrastBrightness(
                    BitmapFactory.decodeResource(
                        this.resources,
                        collection.oldImg!!,
                    ),0.8F , 0.8F
                ))
        val arraySpinner = arrayOf("Alphabetical", "Latest")
        val filterAdapter = ArrayAdapter(requireActivity(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
        val SPFilter = binding.SPDFilter
        SPFilter.adapter = filterAdapter
        val IVBack = binding.IVColBack
        IVBack.setOnClickListener {
            navController.navigateUp()
        }
        val RVRecipe = binding.RVColD

        val recipeArray = collection.recipes
        val recipeAdapter = CollectionRecipeAdapter(recipeArray){ itemClicked ->
            //finish()
            val bundle = Bundle()
            bundle.putString("Title", itemClicked.title)
            bundle.putString("Description", itemClicked.description)
            bundle.putInt("Score", itemClicked.score)
            bundle.putInt("NumScore", itemClicked.numScore)
            bundle.putString("Diff", itemClicked.diff)
            bundle.putInt("Time", itemClicked.time)
            bundle.putString("ProfileName", itemClicked.profile_name)
            bundle.putInt("ProfileImg", itemClicked.profile_img)
            bundle.putInt("Picture", itemClicked.img)
            bundle.putString("VideoUrl", itemClicked.video_url)

            navController.navigate(R.id.goToDetail,bundle)
        }

        RVRecipe.layoutManager = GridLayoutManager(requireActivity(), 2)
        RVRecipe.adapter = recipeAdapter
        val IVGrid = binding.IVColGrid  //Edit pencil icon
        val IVList = binding.IVColList


        IVGrid.setOnClickListener {
            //RVRecipe.layoutManager = GridLayoutManager(requireActivity(), 2)
            recipeAdapter.changeStatus()
            if (IVList.visibility == View.GONE) IVList.visibility = View.VISIBLE
            else IVList.visibility = View.GONE
        }
        /*
        IVList.setOnClickListener {
            RVRecipe.layoutManager = LinearLayoutManager(requireActivity())
        }

        */
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