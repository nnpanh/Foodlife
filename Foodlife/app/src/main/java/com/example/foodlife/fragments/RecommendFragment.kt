package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.*
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.databinding.FragmentHomeBinding
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.view_models.HomeViewModel
import com.example.foodlife.view_models.PlanViewModel
import com.example.foodlife.view_models.RecommendViewModel

class RecommendFragment :Fragment(){
    private lateinit var navController: NavController
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    private var adapterRecommendFrame: RecommendFrameAdapter? = null

     private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        homeViewModel = ViewModelProvider(store)[HomeViewModel::class.java]

        //Initialize view
        //initListener()
        initAdapters()

    }
    /*private fun initListener() {
        binding.ivBack.setOnClickListener(this)
        binding.ivCollapse.setOnClickListener(this)
        binding.ivPlus.setOnClickListener(this)
        binding.ivMinus.setOnClickListener(this)

    }*/
    private fun initAdapters(){
        //Create adapter
        adapterRecommendFrame = RecommendFrameAdapter()

        /*binding.rvRecFrameItem.adapter = adapterRecommendFrame
        binding.rvRecFrameItem.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapterRecommendFrame!!.updateData(homeViewModel.recList)*/


        //Check if recyclerView is not null
        if (adapterRecommendFrame!=null){
            setAdapterRec(adapterRecommendFrame!!,binding.rvRecFrameItem)
            //Load data
            homeViewModel.loadRecommend()
            homeViewModel.recList.value?.let {adapterRecommendFrame!!.updateData(it) }

        }

    }
    private fun setAdapterRec(_adapter:RecommendFrameAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}