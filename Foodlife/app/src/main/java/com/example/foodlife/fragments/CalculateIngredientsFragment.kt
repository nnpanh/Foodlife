package com.example.foodlife.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.IngredientAdapter
import com.example.foodlife.adapters.PlanImageAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.adapters.PlanTotalAdapter
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel


class CalculateIngredientsFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentCalculateIngredientsBinding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: PlanTextAdapter? = null
    private var adapterLunch: PlanTextAdapter? = null
    private var adapterDinner: PlanTextAdapter? = null
    private var adapterSnack: PlanTextAdapter? = null
    private var adapterIngredient: IngredientAdapter? = null

    private lateinit var planViewModel: PlanViewModel
    private var hiddenStateBreakfast = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateIngredientsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        planViewModel = ViewModelProvider(store)[PlanViewModel::class.java]

        //Initialize view
        initListener()
        initTotalAdapter()
        initIngredientsAdapter()
        checkTotal()

    }

    private fun initListener() {
        binding.ivBack.setOnClickListener(this)
        binding.ivCollapseBreakfast.setOnClickListener(this)
        binding.ivPlus.setOnClickListener(this)
        binding.ivMinus.setOnClickListener(this)
    }

    private fun initIngredientsAdapter(){
        adapterIngredient = IngredientAdapter()
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapterIngredient!!.updateData(planViewModel.ingredientList)
    }

    private fun initTotalAdapter() {
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
                    checkTotal()
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
                checkTotal()
            }
        }
        if (planViewModel.lunchList.size ==0) binding.tvLunchDescription.visibility = View.VISIBLE

        if (adapterDinner == null) {
            adapterDinner = PlanTextAdapter(2) { clickedItem ->
                val updateList = planViewModel.dinnerList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.dinnerList = updateList
                    adapterDinner?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
                checkTotal()
            }
        }
        if (planViewModel.dinnerList.size ==0) binding.tvDinnerDescription.visibility = View.VISIBLE


        if (adapterSnack == null) {
            adapterSnack = PlanTextAdapter(3) { clickedItem ->
                val updateList = planViewModel.snackList
                if (updateList.isNotEmpty()) {
                    updateList.remove(clickedItem)
                    planViewModel.snackList = updateList
                    adapterSnack?.updateData(updateList)
                }
                if (updateList.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE
                checkTotal()
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

    private fun setAdapterText(_adapter: PlanTextAdapter, _recyclerView: RecyclerView) {
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.ivBack -> {
                navController.navigateUp()
            }
            R.id.ivCollapseBreakfast -> {
                if (hiddenStateBreakfast) {
                    binding.rvPlanTextBreakfast.visibility = View.VISIBLE
                    binding.rvPlanTextLunch.visibility = View.VISIBLE
                    binding.rvPlanTextDinner.visibility = View.VISIBLE
                    binding.rvPlanTextSnack.visibility = View.VISIBLE
                    binding.ivCollapseBreakfast.setImageResource(R.drawable.open_eye)
                    binding.tvLunchTitle.visibility = View.VISIBLE
                    binding.tvDinnerTitle.visibility = View.VISIBLE
                    binding.tvSnackTitle.visibility = View.VISIBLE

                    binding.tvBreakfastTitle.text="Breakfast"
                    hiddenStateBreakfast = false
                }
                else {
                    binding.rvPlanTextBreakfast.visibility = View.GONE
                    binding.rvPlanTextLunch.visibility = View.GONE
                    binding.rvPlanTextDinner.visibility = View.GONE
                    binding.rvPlanTextSnack.visibility = View.GONE
                    binding.tvLunchTitle.visibility = View.GONE
                    binding.tvDinnerTitle.visibility = View.GONE
                    binding.tvSnackTitle.visibility = View.GONE
                    binding.tvBreakfastTitle.text="Dishes"
                    binding.ivCollapseBreakfast.setImageResource(R.drawable.close_eye)
                    hiddenStateBreakfast = true
                }
            }
            R.id.ivMinus -> {
                planViewModel.ingredientList.forEach { it.selected = false }
                adapterIngredient?.updateData(planViewModel.ingredientList)
                var current : Int = binding.tvNumberServe.text.toString().toInt()
                if (current == 1){
                    showWarning(getString(R.string.warning_less_01))
                } else {
                    current -= 1
                    binding.tvNumberServe.text = current.toString()
                }
            }
            R.id.ivPlus -> {
                planViewModel.ingredientList2.forEach { it.selected = false }
                adapterIngredient?.updateData(planViewModel.ingredientList2)
                var current = Integer.valueOf(binding.tvNumberServe.text.toString())
                current += 1
                binding.tvNumberServe.text = current.toString()
            }
        }
    }

    private fun checkTotal(){
        val totalSize = planViewModel.lunchList.size + planViewModel.dinnerList.size + planViewModel.snackList.size + planViewModel.breakfastList.size
        if (totalSize==0){
            binding.rvIngredients.visibility = View.GONE
            binding.tvTotalDescription.visibility = View.VISIBLE
        }
        else{
            binding.rvIngredients.visibility = View.VISIBLE
            binding.tvTotalDescription.visibility = View.GONE
        }
    }
    private fun showWarning(content: String){
        val textValue = HtmlCompat.fromHtml("<b>Warning</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        val textButton = HtmlCompat.fromHtml("<font color='#FF9C00'>OK</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        val dialogBuilder = AlertDialog.Builder(activity!!)
        dialogBuilder.setMessage(content)
            .setTitle(textValue)
            .setCancelable(false)
            .setPositiveButton(textButton) { dialog, _ ->
                dialog.dismiss()

            }
            .create()
            .show()
    }
}