package com.example.foodlife.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding

class AddRecipeInformationFragment : Fragment(), View.OnClickListener {

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
    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isSpinnerInitial = true

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
                navController.navigate(R.id.addRecipeInformationFragment_to_addRecipeIngredientFragment)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ic_serves_plus -> {
                if (binding.numServes.text.toString()==""){
                    binding.numServes.setText("0")
                }
                else{
                    val a: Int = binding.numServes.text.toString().toInt() + 1
                    binding.numServes.setText(a.toString())
                }

            }
            R.id.ic_serves_minus -> {
                if (binding.numServes.text.toString()==""){
                    binding.numServes.setText("0")
                }else{
                    var a: Int = binding.numServes.text.toString().toInt()
                    if(a > 0){
                        a -= 1
                    }
                    binding.numServes.setText(a.toString())
                }

            }
        }
    }

}