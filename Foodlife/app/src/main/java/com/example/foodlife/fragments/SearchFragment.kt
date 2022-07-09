package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionHomeAdapter
import com.example.foodlife.adapters.MainCategoryAdapter
import com.example.foodlife.adapters.RecommendHomeAdapter
import com.example.foodlife.adapters.SearchRecipeAdapter

import com.example.foodlife.databinding.FragmentSearchBinding
import com.example.foodlife.view_models.HomeViewModel

class SearchFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var adapterSearch: SearchRecipeAdapter? = null


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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
        binding.ivSearchBack.setOnClickListener(this)
        binding.ivSearchFilter.setOnClickListener(this)
    }

    private fun initAdapters(){
        //Create adapter
        //adapterRecommend = RecommendHomeAdapter()
        //adapterMainCat= MainCategoryAdapter() rvMainCat
        //adapterCollection= CollectionHomeAdapter() rvCollectionHome
        if (adapterSearch == null) {
            adapterSearch = SearchRecipeAdapter()
        }
        //Check if recyclerView is not null
        setAdapterSearch(adapterSearch!!, binding.rvSearchRecipe)
        homeViewModel.recList.let { adapterSearch!!.updateData(it) }





    }
    private fun setAdapterSearch(_adapter: SearchRecipeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = GridLayoutManager(context,2)
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.ivSearchBack -> {
                navController.navigateUp()
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}