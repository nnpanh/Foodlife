package com.example.foodlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.DetailDirectionsAdapter
import com.example.foodlife.databinding.FragmentDetailDirectionsBinding
import com.example.foodlife.models.AddRecipe
import com.example.foodlife.models.DetailDirections
import com.example.foodlife.view_models.DetailViewModel

class DetailDirectionsFragment (rep: AddRecipe?): Fragment(){

    private var _binding: FragmentDetailDirectionsBinding? = null
    private val binding get() = _binding!!

    private var adapterDetailDirections: DetailDirectionsAdapter? = null

    private lateinit var detailDirectionsViewModel: DetailViewModel
    private var recipe: AddRecipe? = rep

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

            _binding = FragmentDetailDirectionsBinding.inflate(inflater,container,false)
            // Inflate the layout for this fragment
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailDirectionsViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        initDetailIngredientsAdapter()
    }

    private fun initDetailIngredientsAdapter(){
        adapterDetailDirections = DetailDirectionsAdapter()
        binding.listDir.adapter = adapterDetailDirections
        binding.listDir.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        if(recipe != null){
            adapterDetailDirections!!.updateData(recipe!!.directions)
        }
        else adapterDetailDirections!!.updateData(detailDirectionsViewModel.DetailDirectionsList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}