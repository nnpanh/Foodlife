package com.example.foodlife.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.PlanImageAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel


class PlanFragment : Fragment(),View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: PlanTextAdapter? = null
    private var adapterLunch: PlanTextAdapter? = null
    private var adapterSnack: PlanTextAdapter? = null

    private var adapterImageBreakfast: PlanImageAdapter? = null
    private var adapterImageLunch: PlanImageAdapter? = null
    private var adapterImageSnack: PlanImageAdapter? = null

    private lateinit var planViewModel: PlanViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        planViewModel = ViewModelProvider(store)[PlanViewModel::class.java]


        //Test if roomDB work
        val testUser = UserModel(0, "PA", R.drawable.catcool, 23, null, null)
        val directUser = UserEntity( "Hehe", R.drawable.catcool, 23, null, null)
        val database = activity?.let { FoodlifeDB.getInstance(it.applicationContext) }
        database?.clearAllTables()
        database?.userDAO()?.insert(directUser)

        //Initialize view
        initListener()
        initAdaptersText()

    }

    private fun initListener(){
        binding.ivCalender.setOnClickListener(this)
        binding.ivShopping.setOnClickListener(this)
        binding.ivViewMode.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)

    }
    private fun initAdaptersText(){
        //Create adapter
        adapterBreakfast = PlanTextAdapter(0){ clickedItem ->
            val updateList = planViewModel.breakfastList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.breakfastList.postValue(updateList)
                adapterBreakfast?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvBreakfastDescription.visibility = View.VISIBLE

        }
        adapterLunch = PlanTextAdapter(1){ clickedItem ->
            val updateList = planViewModel.lunchList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.lunchList.postValue(updateList)
                adapterLunch?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvLunchDescription.visibility = View.VISIBLE

        }
        adapterSnack = PlanTextAdapter(3){ clickedItem ->
            val updateList = planViewModel.snackList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.snackList.postValue(updateList)
                adapterSnack?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvSnackDescription.visibility = View.VISIBLE

        }

        //Check if recyclerView is not null
        if (adapterBreakfast!=null){
            setAdapterText(adapterBreakfast!!,binding.rvPlanTextBreakfast)
            //Load data
            planViewModel.loadBreakFast()
            planViewModel.breakfastList.value?.let { adapterBreakfast!!.updateData(it) }

        }
        if (adapterLunch!=null){
            setAdapterText(adapterLunch!!,binding.rvPlanTextLunch)
            planViewModel.loadLunch()
            planViewModel.lunchList.value?.let { adapterLunch!!.updateData(it) }

        }
        if (adapterSnack!=null){
            setAdapterText(adapterSnack!!,binding.rvPlanTextSnack)
            planViewModel.loadSnack()
            planViewModel.snackList.value?.let { adapterSnack!!.updateData(it) }

        }


    }

    private fun initAdaptersImage(){
        //Create adapter
        adapterImageBreakfast = PlanImageAdapter(){ clickedItem ->
            val updateList = planViewModel.breakfastImageList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.breakfastImageList.postValue(updateList)
                adapterImageBreakfast?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvBreakfastDescription.visibility = View.VISIBLE

        }
        adapterImageLunch = PlanImageAdapter(){ clickedItem ->
            val updateList = planViewModel.lunchImageList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.lunchImageList.postValue(updateList)
                adapterImageLunch?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvLunchDescription.visibility = View.VISIBLE

        }
        adapterImageSnack = PlanImageAdapter(){ clickedItem ->
            val updateList = planViewModel.snackImageList.value?.toMutableList()
            if(updateList!!.isNotEmpty()){
                updateList.remove(clickedItem)
                planViewModel.snackImageList.postValue(updateList)
                adapterImageSnack?.updateData(updateList)
            }
            if (updateList.size==0) binding.tvDinnerDescription.visibility = View.VISIBLE

        }

        //Check if recyclerView is not null
        if (adapterImageBreakfast!=null){
            setAdapterImage(adapterImageBreakfast!!,binding.rvPlanTextBreakfast)
            planViewModel.breakfastImageList.value?.let { adapterImageBreakfast!!.updateData(it) }

        }
        if (adapterImageLunch!=null){
            setAdapterImage(adapterImageLunch!!,binding.rvPlanTextLunch)
            planViewModel.lunchImageList.value?.let { adapterImageLunch!!.updateData(it) }

        }
        if (adapterImageSnack!=null){
            setAdapterImage(adapterImageSnack!!,binding.rvPlanTextSnack)
            planViewModel.snackImageList.value?.let { adapterImageSnack!!.updateData(it) }

        }


    }

    private fun setAdapterText(_adapter: PlanTextAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    private fun setAdapterImage(_adapter: PlanImageAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.ivCalender -> {
                navController.navigate(R.id.popUpCalendar)
            }
            R.id.ivShopping -> {
                navController.navigate(R.id.planToCalculate)
                //TODO
            }
            R.id.ivViewMode -> {
                if (planViewModel.viewMode){
                    binding.ivViewMode.setImageResource(R.drawable.category)
                    initAdaptersImage()
                    //Set adapter image
                } else {
                    binding.ivViewMode.setImageResource(R.drawable.list)
                    initAdaptersText()
                    //Set adapter image
                }
                planViewModel.viewMode = planViewModel.viewMode.not()
            }
            R.id.ivBack -> {
                navController.navigate(R.id.returnHome)

            }
        }
    }
}