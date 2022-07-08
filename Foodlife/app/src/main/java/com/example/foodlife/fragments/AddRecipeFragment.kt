package com.example.foodlife.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddrecipeTittleBinding
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.AddRecipeViewModel
import com.example.foodlife.view_models.PlanViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddRecipeFragment : Fragment() {

//    companion object {
//        fun newInstance() = AddRecipeFragment()
//    }
//
//    private lateinit var viewModel: AddRecipeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        return inflater.inflate(R.layout.fragment_addrecipe_tittle, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(AddRecipeViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
    private var _binding: FragmentAddrecipeTittleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val planViewModel =
            ViewModelProvider(this)[PlanViewModel::class.java]

        _binding = FragmentAddrecipeTittleBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val upload: FloatingActionButton = binding.uploadimage
        upload.setOnClickListener{
            val builder = AlertDialog.Builder(this.context)
            builder.setTitle("Select Image")
            builder.setMessage("Choose your option!")
            builder.setPositiveButton("Gallery",{dialog, which ->

            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}