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
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.view_models.HomeViewModel

class RecommendFragment :Fragment(), View.OnClickListener{
    private lateinit var navController: NavController
    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    private var adapterRecommendFrame: RecommendFrameAdapter? = null
    private var adapterRecommendCat: RecommendCategoryAdapter? = null

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
        initAdapters()

    }


    private fun initAdapters(){
        //Create adapter

        //adapterMainCat= MainCategoryAdapter() rvMainCat
        //adapterCollection= CollectionHomeAdapter() rvCollectionHome
        if (adapterRecommendFrame == null) {
            adapterRecommendFrame = RecommendFrameAdapter()
        }

        if (adapterRecommendCat == null) {
            adapterRecommendCat = RecommendCategoryAdapter() { clickedItem ->
                val updateList = homeViewModel.recCat
                //println(1)
            }
        }
        //Check if recyclerView is not null
        setAdapterRec(adapterRecommendFrame!!, binding.rvRecFrameItem)
        homeViewModel.recList.let { adapterRecommendFrame!!.updateData(it) }

        setAdapterRecCat(adapterRecommendCat!!, binding.rvRecMainCat)
        homeViewModel.recCat.let { adapterRecommendCat!!.updateData(it) }




    }
    private fun setAdapterRec(_adapter: RecommendFrameAdapter, _recyclerView: RecyclerView){
        //Set adapter
        //_adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
    private fun setAdapterRecCat(_adapter: RecommendCategoryAdapter, _recyclerView: RecyclerView){
        //Set adapter
        //_adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }


    override fun onClick(p0: View?) {
        /*when (p0?.id) {

            R.id.ivArrow -> {
                //navController.navigate(R.id.homeToRecommendFragment)
                //TODO
            }

        }*/
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}