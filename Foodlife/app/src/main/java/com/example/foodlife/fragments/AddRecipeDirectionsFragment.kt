package com.example.foodlife.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddRecipeDirectionAdapter
import com.example.foodlife.databinding.FragmentAddRecipeDirectionsBinding
import com.example.foodlife.models.AddRecipe
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.DetailDirections
import com.example.foodlife.models.DetailIngredients
import com.example.foodlife.view_models.AddRecipeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeDirectionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeDirectionsFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeDirectionsBinding? = null
    private val binding get() = _binding!!

    private var adapterDirection: AddRecipeDirectionAdapter? = null

    private lateinit var directionViewModel: AddRecipeViewModel

    private var mList: MutableList<AddRecipeDirectionModel> = mutableListOf()
    private val GALLERY_REQ_CODE = 1000
    private var imgPath: Uri? = null

    private var position: Int = 0
    private lateinit var recipe: AddRecipe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeDirectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        directionViewModel = ViewModelProvider(store)[AddRecipeViewModel::class.java]
        mList = directionViewModel.initDirection
        recipe = arguments?.getSerializable("Recipe") as AddRecipe
        initListener()
        initAdapters()
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.arAddDirectionBtn.setOnClickListener(this)
    }

    private fun initAdapters(){
        if (adapterDirection == null){
            adapterDirection = AddRecipeDirectionAdapter (
                {imageClicked ->
                    pickImage()
                    position = mList.indexOf(imageClicked)
                },
                {clickedItem ->
                    if (mList.size>1){
                        mList.remove(clickedItem)
                        adapterDirection!!.updateData(mList)
                    }
                })
            binding.rcvDirectionList.adapter = adapterDirection
            binding.rcvDirectionList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapterDirection!!.updateData(directionViewModel.initDirection)
        }
    }

    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        startActivityForResult(intent, GALLERY_REQ_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                imgPath = data!!.data
                mList[position].imageURI = imgPath!!
                adapterDirection!!.updateData(mList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_btn -> {
//                toListOfDetailDirections()
                navController.navigate(R.id.returnHome)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ar_add_direction_btn ->{
                val item = AddRecipeDirectionModel("Direction", mList.size + 1,null)
                mList.add(item)
                Log.i("số lượng: ", mList.size.toString())
                adapterDirection!!.updateData(mList)
            }
        }
    }

    /*  fun toListOfDetailDirections(){
          for(arm in mList){
              recipe.directions.add(DetailDirections(-1, arm.direcion, "", false, arm.imageURI))
          }
      }*/
}