package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.adapters.CollectionHomeAdapter
import com.example.foodlife.adapters.MainCategoryAdapter
import com.example.foodlife.adapters.RecommendFrameAdapter
import com.example.foodlife.adapters.RecommendHomeAdapter
import com.example.foodlife.databinding.FragmentHomeBinding
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.view_models.HomeViewModel
import com.example.foodlife.view_models.RecommendViewModel

class RecommendFragment :Fragment(){
    private var _binding: FragmentRecommendBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapterRecommendFrame: RecommendFrameAdapter? = null

     lateinit var recommendViewModel: RecommendViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recommendViewModel = ViewModelProvider(this)[recommendViewModel::class.java]

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
    }

    private fun initAdapters(){
        //Create adapter
        adapterRecommendFrame = RecommendFrameAdapter()



        //Check if recyclerView is not null
        if (adapterRecommendFrame!=null){
            setAdapterRec(adapterRecommendFrame!!,binding.rvRecFrameItem)
            //Load data
            recommendViewModel.loadRecommend()
            recommendViewModel.recList.value?.let {adapterRecommendFrame!!.updateData(it) }

        }

    }
    private fun setAdapterRec(_adapter:RecommendFrameAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            //layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}