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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.DetailIngredientsAdapter
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.databinding.FragmentDetailIngredientsBinding
import com.example.foodlife.view_models.DetailViewModel
import com.example.foodlife.view_models.PlanViewModel

class DetailIngredientsFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentDetailIngredientsBinding? = null
    private val binding get() = _binding!!

    private var adapterDetailIngredients: DetailIngredientsAdapter? = null

    private lateinit var detailIngredientsViewModel: DetailViewModel

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

        detailIngredientsViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //Initialize view
        initListener()
//        initTotalAdapter()
        initDetailIngredientsAdapter()

    }

    private fun initListener() {
        binding.ivPlus.setOnClickListener(this)
        binding.ivMinus.setOnClickListener(this)
    }

    private fun initDetailIngredientsAdapter(){
        adapterDetailIngredients = DetailIngredientsAdapter()
        binding.listIngredients.adapter = adapterDetailIngredients
        binding.listIngredients.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapterDetailIngredients!!.updateData(detailIngredientsViewModel.DetailIngredientsList)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.ivMinus -> {
                adapterDetailIngredients?.updateData(detailIngredientsViewModel.DetailIngredientsList)
                binding.tvNumberServe.text="01"
            }
            R.id.ivPlus -> {
                adapterDetailIngredients?.updateData(detailIngredientsViewModel.DetailIngredientsList2)
                binding.tvNumberServe.text="02"
            }
        }
    }
}