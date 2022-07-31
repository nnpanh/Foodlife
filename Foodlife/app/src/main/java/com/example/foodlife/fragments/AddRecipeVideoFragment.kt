package com.example.foodlife.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeVideoBinding
import com.example.foodlife.models.AddRecipe


class AddRecipeVideoFragment : Fragment(), View.OnClickListener {
    private val GALLERY_REQ_CODE = 1000
    private lateinit var mc: MediaController
    private var vidPath: Uri? = null
    private lateinit var navController: NavController
    private lateinit var recipe: AddRecipe
    private var _binding: FragmentAddRecipeVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRecipeVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        mc = MediaController(requireContext())
        recipe = arguments?.getSerializable("Recipe") as AddRecipe
        initListener()
    }

    private fun initListener() {
        binding.continueBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.ivUploadvideo.setOnClickListener(this)
        binding.videoView.setMediaController(mc)
        binding.ivEditvideo.setOnClickListener(this)
        binding.ivDeletevideo.setOnClickListener(this)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.continue_btn -> {
                recipe.vidUri = vidPath
                navController.navigate(R.id.addRecipeVideoFragment_to_addRecipeInformationFragment, bundleOf("Recipe" to recipe))
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.iv_uploadvideo -> {
                pickVideo()
                binding.ivUploadvideo.visibility = View.GONE
                binding.tvUploadTxt.visibility = View.GONE
                binding.ivEditvideo.visibility = View.VISIBLE
                binding.ivDeletevideo.visibility = View.VISIBLE
            }
            R.id.iv_editvideo -> {
                pickVideo()
            }
            R.id.iv_deletevideo -> {
                binding.videoView.setVideoURI(null)
                vidPath = null
                binding.ivUploadvideo.visibility = View.VISIBLE
                binding.tvUploadTxt.visibility = View.VISIBLE
                binding.ivEditvideo.visibility = View.GONE
                binding.ivDeletevideo.visibility = View.GONE
                binding.videoView.visibility = View.GONE
            }
        }
    }

    private fun pickVideo(){
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Video"),
            GALLERY_REQ_CODE
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                binding.videoView.setVideoURI(data!!.data)
                binding.videoView.visibility = View.VISIBLE
                vidPath = data.data
            }
        }
    }
}