package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.example.foodlife.R
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.adapters.CollectionHomeAdapter
import com.example.foodlife.adapters.MainCategoryAdapter
import com.example.foodlife.adapters.RecommendHomeAdapter
import com.example.foodlife.databinding.FragmentHomeBinding
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.models.Recipe
import com.example.foodlife.view_models.HomeViewModel
import com.example.foodlife.view_models.PlanViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapterRecommend:RecommendHomeAdapter? = null
    private var adapterMainCat: MainCategoryAdapter? = null
    private var adapterCollection: CollectionHomeAdapter? = null

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
    }

    private fun initAdapters(){
        //Create adapter
        adapterRecommend = RecommendHomeAdapter()
        adapterMainCat= MainCategoryAdapter()
        adapterCollection= CollectionHomeAdapter()


        //Check if recyclerView is not null
        if (adapterRecommend!=null){
            setAdapterRec(adapterRecommend!!,binding.rvRecommended)
            //Load data
            homeViewModel.loadRecommend()
            homeViewModel.recList.value?.let {adapterRecommend!!.updateData(it) }

        }
        if (adapterMainCat!=null){
            setAdapterMain(adapterMainCat!!,binding.rvMainCat)
            //Load data
            homeViewModel.loadMainList()
            homeViewModel.mainList.value?.let {adapterMainCat!!.updateData(it) }

        }
        if (adapterCollection!=null){
            setAdapterCollection(adapterCollection!!,binding.rvCollectionHome)
            //Load data
            homeViewModel.loadCollection()
            homeViewModel.collectionList.value?.let {adapterCollection!!.updateData(it) }

        }
    }
    private fun setAdapterRec(_adapter: RecommendHomeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            //layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
    private fun setAdapterMain(_adapter: MainCategoryAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            //layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
    private fun setAdapterCollection(_adapter: CollectionHomeAdapter, _recyclerView: RecyclerView){
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