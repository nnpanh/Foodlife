package com.example.foodlife.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding
import com.example.foodlife.models.AddRecipe

class AddRecipeInformationFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isSpinnerInitial = true
    private lateinit var recipe: AddRecipe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)

        recipe = arguments?.getSerializable("Recipe") as AddRecipe

        val arraySpinner = arrayOf("Easy", "Medium", "Hard")
        val dropdownAdapter = ArrayAdapter(requireActivity(), R.layout.item_spinner, arraySpinner)
        dropdownAdapter.setDropDownViewResource(R.layout.item_spinner)
            //ArrayAdapter(requireActivity(),com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
        binding.difficultyDropdown.adapter = dropdownAdapter

        binding.difficultyDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isSpinnerInitial)
                    isSpinnerInitial = false
                else{
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {}

        }
        initListener()
    }

    private fun initListener() {
        binding.continueBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.icServesPlus.setOnClickListener(this)
        binding.icServesMinus.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.continue_btn -> {
                if(binding.numServes.text.toString()=="") {
                    recipe.serves = -1
                } else recipe.serves = binding.numServes.text.toString().toInt()

                if(binding.edCooktime.text.toString()=="") {
                    recipe.cookTime = -1
                } else recipe.cookTime = binding.edCooktime.text.toString().toInt()

                if(binding.edPreptime.text.toString()=="") {
                    recipe.prepTime = -1
                } else recipe.prepTime = binding.edPreptime.text.toString().toInt()

                recipe.difficulty = binding.difficultyDropdown.selectedItem.toString()
                Log.e("difficulty", recipe.difficulty)
                recipe.name?.let { Log.e("name", it) }
                navController.navigate(R.id.addRecipeInformationFragment_to_addRecipeIngredientFragment, bundleOf("Recipe" to recipe))
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ic_serves_plus -> {
                if (binding.numServes.text.toString()==""){
                    binding.numServes.setText("1")
                }
                else{
                    val a: Int = binding.numServes.text.toString().toInt() + 1
                    binding.numServes.setText(a.toString())
                }

            }
            R.id.ic_serves_minus -> {
                if (binding.numServes.text.toString()==""){
                    binding.numServes.setText("1")
                }else{
                    var a: Int = binding.numServes.text.toString().toInt()
                    if(a > 1){
                        a -= 1
                    }
                    binding.numServes.setText(a.toString())
                }

            }
        }
    }

}