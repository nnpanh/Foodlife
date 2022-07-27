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
import com.example.foodlife.adapters.*
import com.example.foodlife.databinding.FragmentChoiceBinding
import com.example.foodlife.view_models.HomeViewModel

class ChoiceFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentChoiceBinding ?= null
    private val binding get() = _binding!!

    private var adapterList: ChoiceRecipeAdapter? = null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
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
        if (homeViewModel.meatList.value!!.isEmpty())
            homeViewModel.loadMeatList()
        if (homeViewModel.vegetableList.value!!.isEmpty())
            homeViewModel.loadVegetableList()
        if (homeViewModel.vietnamList.value!!.isEmpty())
            homeViewModel.loadVietNamList()
        if (homeViewModel.westernList.value!!.isEmpty())
            homeViewModel.loadWesternList()
        if (homeViewModel.dessertList.value!!.isEmpty())
            homeViewModel.loadDessertList()

        if (arguments!=null){
            val choice = arguments?.getString("choice")
            if (choice=="0"){
                binding.tvTitle.text="Recommend for you"
                homeViewModel.collectionList.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="1"){
                binding.tvTitle.text="Asian"
                homeViewModel.vietnamList.value.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="2"){
                binding.tvTitle.text="Western"
                homeViewModel.westernList.value.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="3"){
                binding.tvTitle.text="Drinks"
                homeViewModel.drinkList.let { adapterList!!.updateData(it) }
            }
            else if (choice=="4"){
                binding.tvTitle.text="Dessert"
                homeViewModel.dessertList.value.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="5"){
                binding.tvTitle.text="Salad"
                homeViewModel.vegetableList.value.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="6"){
                binding.tvTitle.text="Party"
                homeViewModel.meatList.value.let { adapterList!!.updateData(it!!) }
            }
            else if (choice=="7"){
                binding.tvTitle.text="Recently Viewed"
                homeViewModel.recentlyList.let { adapterList!!.updateData(it) }
            }
        }

    }

    private fun initListener() {
        binding.ivSearchBack.setOnClickListener(this)
    }

    private fun initAdapters(){
        //Create adapter
        if (adapterList == null) {
            adapterList = ChoiceRecipeAdapter(){ itemClicked ->
                val bundle = Bundle()
                bundle.putString("Title", itemClicked.title)
                bundle.putString("Description", itemClicked.description)
                bundle.putInt("Score", itemClicked.score)
                bundle.putInt("NumScore", itemClicked.numScore)
                bundle.putString("Diff", itemClicked.diff)
                bundle.putInt("Time", itemClicked.time)
                bundle.putString("ProfileName", itemClicked.profile_name)
                bundle.putInt("ProfileImg", itemClicked.profile_img)
                bundle.putInt("Picture", itemClicked.img)
                bundle.putString("VideoUrl", itemClicked.video_url)
                navController.navigate(R.id.choiceToDetail,bundle)

            }

        }

        //Check if recyclerView is not null
        setAdapterRec(adapterList!!, binding.rvSearchRecipe)
        homeViewModel.collectionList.let { adapterList!!.updateData(it) }



    }
    private fun setAdapterRec(_adapter: ChoiceRecipeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivSearchBack->{
                navController.navigate(R.id.choiceToHome)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}