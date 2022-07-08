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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.adapters.CollectionHomeAdapter
import com.example.foodlife.adapters.MainCategoryAdapter
import com.example.foodlife.adapters.RecommendHomeAdapter
import com.example.foodlife.databinding.FragmentHomeBinding
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.dialog.CalendarPopUp
import com.example.foodlife.models.Recipe
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.HomeViewModel
import com.example.foodlife.view_models.PlanViewModel


class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var adapterRecommend:RecommendHomeAdapter? = null
    private var adapterMainCat: MainCategoryAdapter? = null
    private var adapterCollection: CollectionHomeAdapter? = null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        homeViewModel = ViewModelProvider(store)[HomeViewModel::class.java]


        //Initialize view
        initListener()
        initAdapters()

    }

    private fun initListener() {
        binding.ivArrow.setOnClickListener(this)
        binding.ivArrowCollect.setOnClickListener(this)
        binding.ivCreateMeal.setOnClickListener(this)
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
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
    private fun setAdapterMain(_adapter: MainCategoryAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
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

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.ivArrow -> {
                navController.navigate(R.id.homeToRecommendFragment)
                //TODO
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}