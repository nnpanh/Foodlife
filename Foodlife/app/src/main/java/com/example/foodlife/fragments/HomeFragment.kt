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
import com.example.foodlife.adapters.PlanTextAdapter
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
        binding.HomeSearch.setOnClickListener(this)
    }

    private fun initAdapters(){
        //Create adapter
        //adapterRecommend = RecommendHomeAdapter()
        //adapterMainCat= MainCategoryAdapter() rvMainCat
        //adapterCollection= CollectionHomeAdapter() rvCollectionHome
        if (adapterRecommend == null) {
            adapterRecommend = RecommendHomeAdapter(){ itemClicked ->
                val bundle = Bundle()
                bundle.putString("Title", itemClicked.title)
                bundle.putString("Description", itemClicked.description)
                bundle.putInt("Score", itemClicked.score)
                bundle.putString("Diff", itemClicked.diff)
                bundle.putInt("Time", itemClicked.time)
                bundle.putString("ProfileName", itemClicked.profile_name)
                bundle.putInt("ProfileImg", itemClicked.profile_img)
                navController.navigate(R.id.homeToDetail,bundle)

            }

        }

        if (adapterMainCat == null) {
            adapterMainCat = MainCategoryAdapter()
        }

        if (adapterCollection == null) {
            adapterCollection = CollectionHomeAdapter(){ itemClicked ->
                val bundle = Bundle()
                bundle.putString("Title", itemClicked.title)
                bundle.putString("Description", itemClicked.description)
                bundle.putInt("Score", itemClicked.score)
                bundle.putString("Diff", itemClicked.diff)
                bundle.putInt("Time", itemClicked.time)
                bundle.putString("ProfileName", itemClicked.profile_name)
                bundle.putInt("ProfileImg", itemClicked.profile_img)
                navController.navigate(R.id.homeToDetail,bundle)

            }
        }
        //Check if recyclerView is not null
        setAdapterRec(adapterRecommend!!, binding.rvRecommended)
        homeViewModel.recList.let { adapterRecommend!!.updateData(it) }

        setAdapterMain(adapterMainCat!!, binding.rvMainCat)
        homeViewModel.mainList.let { adapterMainCat!!.updateData(it) }

        setAdapterCollection(adapterCollection!!, binding.rvCollectionHome)
        homeViewModel.collectionList.let { adapterCollection!!.updateData(it) }




    }
    private fun setAdapterRec(_adapter: RecommendHomeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
    private fun setAdapterMain(_adapter: MainCategoryAdapter, _recyclerView: RecyclerView){
        //Set adapter
        //_adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }
    private fun setAdapterCollection(_adapter: CollectionHomeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        //_adapter.setHasStableIds(true)
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
            R.id.HomeSearch->{
                navController.navigate(R.id.homeToSearchFragment)
            }
            R.id.ivArrowCollect->{
                navController.navigate(R.id.homeToSearchFragment)
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}