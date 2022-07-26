package com.example.foodlife.fragments

import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.RecommendCategoryAdapter
import com.example.foodlife.adapters.RecommendFrameAdapter
import com.example.foodlife.databinding.FragmentRecommendBinding
import com.example.foodlife.dialog.AddToCollectionDialog
import com.example.foodlife.view_models.HomeViewModel
import com.google.android.material.snackbar.Snackbar


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

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        homeViewModel = ViewModelProvider(store)[HomeViewModel::class.java]

        if (homeViewModel.meatList.value!!.isEmpty())
            homeViewModel.loadMeatList()
        if (homeViewModel.vegetableList.value!!.isEmpty())
            homeViewModel.loadVegetableList()
        if (homeViewModel.westernList.value!!.isEmpty())
            homeViewModel.loadWesternList()
        if (homeViewModel.vietnamList.value!!.isEmpty())
            homeViewModel.loadVietNamList()
        if (homeViewModel.dessertList.value!!.isEmpty())
            homeViewModel.loadDessertList()

        //Initialize view
        initListener()
        initAdapters()



    }
    private fun initListener() {
        binding.ivSearchBack.setOnClickListener(this)
    }


    private fun initAdapters(){
        //Create adapter

        if (adapterRecommendFrame == null) {
            adapterRecommendFrame = RecommendFrameAdapter({ itemClicked ->
                val bundle = Bundle()

                bundle.putString("Title", itemClicked.title)
                bundle.putString("Description", itemClicked.description)
                bundle.putInt("Score", itemClicked.score)
                bundle.putString("Diff", itemClicked.diff)
                bundle.putInt("Time", itemClicked.time)
                bundle.putString("ProfileName", itemClicked.profile_name)
                bundle.putInt("ProfileImg", itemClicked.profile_img)
                bundle.putInt("Picture", itemClicked.img)
                bundle.putString("VideoUrl", itemClicked.video_url)
                navController.navigate(R.id.recToDetail,bundle)

            },{
                saveToCollection()
            })
        }

        if (adapterRecommendCat == null) {
            adapterRecommendCat = RecommendCategoryAdapter() { clickedItem ->
                val updateList = homeViewModel.recCat
                val position=updateList.indexOf(clickedItem)

                var newList=homeViewModel.recList
                if (position==0) {
                    newList=homeViewModel.meatList.value!!
                }
                else if (position==1){
                    newList=homeViewModel.vegetableList.value!!
                }
                else if (position==2){
                    newList=homeViewModel.westernList.value!!
                }
                else if (position==3){
                    newList=homeViewModel.vietnamList.value!!
                }
                else if (position==4){
                    newList=homeViewModel.dessertList.value!!
                }
                binding.rvRecFrameItem.scrollToPosition(0)
                    //planViewModel.breakfastList = updateList
                adapterRecommendFrame?.updateData(newList)
            }
        }

        //Check if recyclerView is not null
        setAdapterRec(adapterRecommendFrame!!, binding.rvRecFrameItem)
        homeViewModel.meatList.value.let { adapterRecommendFrame!!.updateData(it!!) }

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
        when (p0?.id) {


            R.id.ivSearchBack->{
                navController.navigate(R.id.recToHome)
            }

        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveToCollection() {
        val addToCollectionBottomDialog = AddToCollectionDialog()
        addToCollectionBottomDialog.show(parentFragmentManager, AddToCollectionDialog.TAG)
        addToCollectionBottomDialog.setFragmentResultListener("request_key") { _, bundle ->
            val addNewCollection = bundle.getBoolean("add",false)
            if (addNewCollection){
                navController.navigate(R.id.returnCollection,Bundle().apply {
                    putBoolean("add",true)
                })}
            else{
                navController.navigate(R.id.returnCollection,bundle)
                this.view?.let{
                    Snackbar.make(this.view!!, "Saved successfully", Snackbar.LENGTH_LONG)
                        .show()
                }
            }


        }
    }
}