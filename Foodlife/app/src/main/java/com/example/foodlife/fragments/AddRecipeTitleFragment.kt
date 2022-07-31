package com.example.foodlife.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeTitleBinding
import com.example.foodlife.models.AddRecipe
import com.example.foodlife.view_models.AddRecipeViewModel
import java.io.FileNotFoundException
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeTitleFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private val GALLERY_REQ_CODE = 1000
    private var IVImage: ImageView? = null
    private var imgPath: Uri? = null
    private lateinit var navController: NavController
    private lateinit var addRecipeViewModel: AddRecipeViewModel
    private lateinit var recipe: AddRecipe
    private var _binding: FragmentAddRecipeTitleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addRecipeViewModel = ViewModelProvider(this)[AddRecipeViewModel::class.java]
        _binding = FragmentAddRecipeTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        recipe = AddRecipe("","",null, null,0,0,0,"", mutableListOf(), mutableListOf())
        initListener()
    }

    private fun initListener() {
        binding.continueBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.ivUploadimage.setOnClickListener(this)
        binding.ivEditimage.setOnClickListener(this)
        binding.ivDeleteimage.setOnClickListener(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.continue_btn -> {
                recipe.name = binding.etRecipeName.text.toString()
                recipe.description = binding.etDescription.text.toString()
                recipe.imgUri = imgPath
                navController.navigate(R.id.addRecipeTitleFragment_to_addRecipeVideoFragment, bundleOf("Recipe" to recipe))
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.iv_uploadimage -> {
                pickImage()
            }
            R.id.iv_editimage -> {
                pickImage()
            }
            R.id.iv_deleteimage -> {
                binding.ivImagePlaceholder.setImageResource(R.drawable.image_placeholder)
                binding.ivUploadimage.visibility = View.VISIBLE
                binding.tvUploadTxt.visibility = View.VISIBLE
                binding.ivEditimage.visibility = View.GONE
                binding.ivDeleteimage.visibility = View.GONE
                imgPath = null
            }
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
                binding.ivImagePlaceholder.setImageURI(data!!.data)
                binding.ivUploadimage.visibility = View.GONE
                binding.tvUploadTxt.visibility = View.GONE
                binding.ivEditimage.visibility = View.VISIBLE
                binding.ivDeleteimage.visibility = View.VISIBLE
                imgPath = data.data
                val path: Drawable? = imgPath?.let { createDrawableResource(it) }
                Log.e("image", imgPath.toString())
            }
        }
    }

    fun createDrawableResource(uri: Uri): Drawable? {
        try {
            val inputStream: InputStream? = requireActivity().contentResolver.openInputStream(uri)
            return Drawable.createFromStream(inputStream, uri.toString())
        } catch (e: FileNotFoundException) {
            return null
        }
    }

}