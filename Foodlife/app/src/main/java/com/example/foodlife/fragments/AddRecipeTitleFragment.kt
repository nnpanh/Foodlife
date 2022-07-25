package com.example.foodlife.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding
import com.example.foodlife.databinding.FragmentAddRecipeTitleBinding

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
    private var _binding: FragmentAddRecipeTitleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddRecipeTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        initListener()
    }

    private fun initListener() {
        binding.continueBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.ivUploadimage.setOnClickListener(this)
        binding.ivImagePlaceholder.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.continue_btn -> {
                navController.navigate(R.id.addRecipeTittleFragment_to_addRecipeInformationFragment)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.iv_uploadimage -> {
                pickImage()
            }
            R.id.iv_image_placeholder -> {
                pickImage()
            }
        }
    }

    fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, GALLERY_REQ_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                binding.ivImagePlaceholder.setImageURI(data!!.data)
                binding.ivUploadimage.visibility = View.INVISIBLE
                binding.tvUploadTxt.visibility = View.GONE
                imgPath = data.data
            }
        }
    }
}