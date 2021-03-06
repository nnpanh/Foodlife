package com.example.foodlife.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.PlanImageAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.databinding.FragmentPlanV2Binding
import com.example.foodlife.dialog.CalendarPopUp
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel
import com.google.android.material.datepicker.MaterialDatePicker


class PlanFragmentV2 : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentPlanV2Binding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: PlanTextAdapter? = null
    private var adapterLunch: PlanTextAdapter? = null
    private var adapterDinner: PlanTextAdapter? = null
    private var adapterSnack: PlanTextAdapter? = null

    private var adapterImageBreakfast: PlanImageAdapter? = null
    private var adapterImageLunch: PlanImageAdapter? = null
    private var adapterImageDinner: PlanImageAdapter? = null
    private var adapterImageSnack: PlanImageAdapter? = null

    private lateinit var planViewModel: PlanViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanV2Binding.inflate(inflater, container, false)
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




        //Initialize view
        if (arguments!=null){
            /**
             * Fragment started from addToPlan
             */
            val newDish = PlanItemModel(
                arguments!!.getString("Title","Some random dish"),
                arguments!!.getString("Rate","4.5"),
                arguments!!.getInt("Image",R.drawable.recommend_1),
                arguments!!.getString("Time","15 mins"),
                arguments!!.getString("Level","Hard"),
                arguments!!.getString("Author","NKTTNga")
            )
            if (arguments!!.getBoolean("Breakfast",false)){
                planViewModel.breakfastList.add(newDish)
            }
            if (arguments!!.getBoolean("Lunch",false)){
                planViewModel.lunchList.add(newDish)
            }
            if (arguments!!.getBoolean("Dinner",false)){
                planViewModel.dinnerList.add(newDish)
            }
            if (arguments!!.getBoolean("Snack",false)){
                planViewModel.snackList.add(newDish)
            }
            planViewModel.ingredientList.add(Ingredient("added ingredients","8"))
            planViewModel.ingredientList2.add(Ingredient("added ingredients","16"))
        arguments = null
        }
        initListener()
        initAdaptersText()

    }

    private fun initListener() {
        binding.ivCalender.setOnClickListener(this)
        binding.ivShopping.setOnClickListener(this)
        binding.ivViewMode.setOnClickListener(this)
    }

    private fun initAdaptersText() {
        //Create adapter
        if (adapterBreakfast == null) {
            adapterBreakfast = PlanTextAdapter(0,false) { clickedItem ->
                val updateList = planViewModel.breakfastList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.breakfastList = updateList
                    adapterBreakfast?.updateData(updateList)
                    if (updateList.size == 0) binding.tvBreakfastDescription.visibility =
                        View.VISIBLE
                }
            }
        }
        if (planViewModel.breakfastList.size ==0) binding.tvBreakfastDescription.visibility = View.VISIBLE


        if (adapterLunch == null) {
            adapterLunch = PlanTextAdapter(1,false) { clickedItem ->
                val updateList = planViewModel.lunchList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.lunchList = updateList
                    adapterLunch?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.lunchList.size ==0) binding.tvLunchDescription.visibility = View.VISIBLE

        if (adapterDinner == null) {
            adapterDinner = PlanTextAdapter(2,false) { clickedItem ->
                val updateList = planViewModel.dinnerList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.dinnerList = updateList
                    adapterDinner?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.dinnerList.size ==0) binding.tvDinnerDescription.visibility = View.VISIBLE


        if (adapterSnack == null) {
            adapterSnack = PlanTextAdapter(3,false) { clickedItem ->
                val updateList = planViewModel.snackList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.snackList = updateList
                    adapterSnack?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.snackList.size ==0) binding.tvSnackDescription.visibility = View.VISIBLE


        //Check if recyclerView is not null
        setAdapterText(adapterBreakfast!!, binding.rvPlanTextBreakfast)
        planViewModel.breakfastList.let { adapterBreakfast!!.updateData(it) }

        setAdapterText(adapterLunch!!, binding.rvPlanTextLunch)
        planViewModel.lunchList.let { adapterLunch!!.updateData(it) }

        setAdapterText(adapterDinner!!, binding.rvPlanTextDinner)
        planViewModel.dinnerList.let { adapterDinner!!.updateData(it) }

        setAdapterText(adapterSnack!!, binding.rvPlanTextSnack)
        planViewModel.snackList.let { adapterSnack!!.updateData(it) }
    }

    private fun initAdaptersImage() {
        //Create adapter
        if (adapterImageBreakfast == null) {
            adapterImageBreakfast = PlanImageAdapter { clickedItem ->
                val updateList = planViewModel.breakfastList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.breakfastList = updateList
                    adapterImageBreakfast?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvBreakfastDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.breakfastList.size ==0) binding.tvBreakfastDescription.visibility = View.VISIBLE

        if (adapterImageLunch == null) {
            adapterImageLunch = PlanImageAdapter { clickedItem ->
                val updateList = planViewModel.lunchList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.lunchList = updateList
                    adapterImageLunch?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.lunchList.size ==0) binding.tvLunchDescription.visibility = View.VISIBLE

        if (adapterImageDinner == null) {
            adapterImageDinner = PlanImageAdapter { clickedItem ->
                val updateList = planViewModel.dinnerList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.dinnerList = updateList
                    adapterImageDinner?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.snackList.size ==0) binding.tvDinnerDescription.visibility = View.VISIBLE

        if (adapterImageSnack == null) {
            adapterImageSnack = PlanImageAdapter { clickedItem ->
                val updateList = planViewModel.snackList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.snackList = updateList
                    adapterImageSnack?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.snackList.size ==0) binding.tvSnackDescription.visibility = View.VISIBLE


        //Check if recyclerView is not null
        setAdapterImage(adapterImageBreakfast!!, binding.rvPlanTextBreakfast)
        planViewModel.breakfastList.let { adapterImageBreakfast!!.updateData(it) }

        setAdapterImage(adapterImageLunch!!, binding.rvPlanTextLunch)
        planViewModel.lunchList.let { adapterImageLunch!!.updateData(it) }

        setAdapterImage(adapterImageDinner!!, binding.rvPlanTextDinner)
        planViewModel.dinnerList.let { adapterImageDinner!!.updateData(it) }


        setAdapterImage(adapterImageSnack!!, binding.rvPlanTextSnack)
        planViewModel.snackList.let { adapterImageSnack!!.updateData(it) }
    }

    private fun setAdapterText(_adapter: PlanTextAdapter, _recyclerView: RecyclerView) {
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    private fun setAdapterImage(_adapter: PlanImageAdapter, _recyclerView: RecyclerView) {
        //Set adapter
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
        when (p0?.id) {
            R.id.ivCalender -> {
                CalendarPopUp().show(childFragmentManager, CalendarPopUp.TAG)
            }
            R.id.ivShopping -> {
                navController.navigate(R.id.planToCalculate)
                //TODO
            }
            R.id.ivViewMode -> {
                if (planViewModel.viewMode) {
                    binding.ivViewMode.setImageResource(R.drawable.category)
                    initAdaptersImage()
                } else {
                    binding.ivViewMode.setImageResource(R.drawable.list)
                    initAdaptersText()
                }
                planViewModel.viewMode = planViewModel.viewMode.not()
            }
        }
    }
}

//Test if roomDB work
//        val testUser = UserModel(0, "PA", R.drawable.catcool, 23, null, null)
//        val directUser = UserEntity("Hehe", R.drawable.catcool, 23, null, null)
//        val database = activity?.let { FoodlifeDB.getInstance(it.applicationContext) }
//        database?.clearAllTables()
//        database?.userDAO()?.insert(directUser)