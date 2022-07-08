package com.example.foodlife.fragments

import android.content.SharedPreferences
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
import com.example.foodlife.adapters.PlanImageAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.dialog.CalendarPopUp
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel
import com.google.android.material.datepicker.MaterialDatePicker


class PlanFragment : Fragment(), View.OnClickListener {
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
        val directUser = UserEntity("Hehe", R.drawable.catcool, 23, null, null)
        val database = activity?.let { FoodlifeDB.getInstance(it.applicationContext) }
        database?.clearAllTables()
        database?.userDAO()?.insert(directUser)

        //Initialize view
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
            adapterBreakfast = PlanTextAdapter(0) { clickedItem ->
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
            adapterLunch = PlanTextAdapter(1) { clickedItem ->
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


        if (adapterSnack == null) {
            adapterSnack = PlanTextAdapter(3) { clickedItem ->
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

        if (adapterImageSnack == null) {
            adapterImageSnack = PlanImageAdapter { clickedItem ->
                val updateList = planViewModel.snackList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.snackList = updateList
                    adapterImageSnack?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
            }
        }
        if (planViewModel.snackList.size ==0) binding.tvSnackDescription.visibility = View.VISIBLE


        //Check if recyclerView is not null
        setAdapterImage(adapterImageBreakfast!!, binding.rvPlanTextBreakfast)
        planViewModel.breakfastList.let { adapterImageBreakfast!!.updateData(it) }


        setAdapterImage(adapterImageLunch!!, binding.rvPlanTextLunch)
        planViewModel.lunchList.let { adapterImageLunch!!.updateData(it) }

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