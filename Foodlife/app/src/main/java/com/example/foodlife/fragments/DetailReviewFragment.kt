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
import com.example.foodlife.databinding.FragmentDetailReviewBinding
import com.example.foodlife.models.DetailReview
import com.example.foodlife.view_models.DetailViewModel

class DetailReviewFragment : Fragment(){

    private var _binding: FragmentDetailReviewBinding? = null
    private val binding get() = _binding!!

    private var adapterDetailReview: DetailReviewAdapter? = null

    private lateinit var detailReviewViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailReviewBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailReviewViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        initDetailIngredientsAdapter()
    }

    private fun initDetailIngredientsAdapter(){
        adapterDetailReview = DetailReviewAdapter()
        binding.listReview.adapter = adapterDetailReview
        binding.listReview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapterDetailReview!!.updateData(detailReviewViewModel.DetailReviewList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}