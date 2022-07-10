package com.example.foodlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.databinding.FragmentDetailIngredientsBinding
import com.example.foodlife.models.DetailIngredient
import com.example.foodlife.view_models.DetailViewModel
import com.example.foodlife.view_models.PlanViewModel

class DetailIngredientsFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentDetailIngredientsBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailIngredientViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailIngredientsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        detailIngredientViewModel = ViewModelProvider(store)[DetailViewModel::class.java]

        //Initialize view
        initListener()
//        initTotalAdapter()
//        initIngredientsAdapter()

    }

    private fun initListener() {
        binding.ivPlus.setOnClickListener(this)
        binding.ivMinus.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}