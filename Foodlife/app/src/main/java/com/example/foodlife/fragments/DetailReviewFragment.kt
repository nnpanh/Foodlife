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
import com.example.foodlife.adapters.DetailReviewAdapter
import com.example.foodlife.databinding.FragmentPaTestBinding
import com.example.foodlife.models.AddRecipe
import com.example.foodlife.models.DetailReview
import com.example.foodlife.view_models.DetailViewModel

class DetailReviewFragment (rep: AddRecipe?): Fragment(){

    private var _binding: FragmentPaTestBinding? = null
    private val binding get() = _binding!!

    private var adapterDetailReview: DetailReviewAdapter? = null

    private lateinit var detailReviewViewModel: DetailViewModel
    private var recipe: AddRecipe? = rep

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPaTestBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailReviewViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        initDetailIngredientsAdapter()
        binding.buttonSendComment.setOnClickListener {
            binding.etComment.setText("")
            binding.etComment.clearFocus()
        }
    }

    private fun initDetailIngredientsAdapter(){
        adapterDetailReview = DetailReviewAdapter()
        binding.listReview.adapter = adapterDetailReview
        binding.listReview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        if(recipe != null)
            adapterDetailReview!!.updateData(detailReviewViewModel.DefaultReviewList)
        else adapterDetailReview!!.updateData(detailReviewViewModel.DetailReviewList)

        //get item count
        if (adapterDetailReview!!.itemCount != 0)
            binding.tvDetailInfo.text = adapterDetailReview!!.itemCount.toString() + " comment(s), " + adapterDetailReview!!.imageCount.toString() + " showcase(s)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}