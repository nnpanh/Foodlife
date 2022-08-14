package com.example.foodlife.fragments

import android.app.AlertDialog
import android.os.Bundle
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
import com.example.foodlife.adapters.CollectionHomeAdapter
import com.example.foodlife.adapters.IngredientAdapter
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.view_models.PlanViewModel


class CalculateIngredientsFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentCalculateIngredientsBinding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: CollectionHomeAdapter? = null
    private var adapterLunch: CollectionHomeAdapter? = null
    private var adapterDinner: CollectionHomeAdapter? = null
    private var adapterSnack: CollectionHomeAdapter? = null
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

    private fun initIngredientsAdapter() {
        adapterIngredient = IngredientAdapter()
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapterIngredient!!.updateData(planViewModel.ingredientList)
    }

    private fun initTotalAdapter() {
        //Create adapter
        if (adapterBreakfast == null) {
            adapterBreakfast = CollectionHomeAdapter { clickedItem ->
            }
        }
        if (planViewModel.morning.size == 0) binding.tvBreakfastDescription.visibility =
            View.VISIBLE


        if (adapterLunch == null) {
            adapterLunch = CollectionHomeAdapter { clickedItem ->
            }
        }
        if (planViewModel.lunch.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE

        if (adapterDinner == null) {
            adapterDinner = CollectionHomeAdapter { clickedItem ->
            }
        }
        if (planViewModel.dinner.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE

        if (adapterSnack == null) {
            adapterSnack = CollectionHomeAdapter { clickedItem ->
            }
        }
        if (planViewModel.snack.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE

        //Check if recyclerView is not null
        setAdapterText(adapterBreakfast!!, binding.rvPlanTextBreakfast)
        planViewModel.morning.let { adapterBreakfast!!.updateData(it) }

        setAdapterText(adapterLunch!!, binding.rvPlanTextLunch)
        planViewModel.lunch.let { adapterLunch!!.updateData(it) }

        setAdapterText(adapterDinner!!, binding.rvPlanTextDinner)
        planViewModel.dinner.let { adapterDinner!!.updateData(it) }

        setAdapterText(adapterSnack!!, binding.rvPlanTextSnack)
        planViewModel.snack.let { adapterSnack!!.updateData(it) }
    }


    private fun setAdapterText(_adapter: CollectionHomeAdapter, _recyclerView: RecyclerView) {
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
                    binding.tvBreakfastTitle.text = "Breakfast"
                    hiddenStateBreakfast = false
                    binding.tvDinnerDescription.visibility = View.VISIBLE
                    binding.tvBreakfastDescription.visibility = View.VISIBLE
                    binding.tvLunchDescription.visibility = View.VISIBLE
                    binding.tvSnackDescription.visibility = View.VISIBLE
                } else {
                    binding.rvPlanTextBreakfast.visibility = View.GONE
                    binding.rvPlanTextLunch.visibility = View.GONE
                    binding.rvPlanTextDinner.visibility = View.GONE
                    binding.rvPlanTextSnack.visibility = View.GONE
                    binding.tvLunchTitle.visibility = View.GONE
                    binding.tvDinnerTitle.visibility = View.GONE
                    binding.tvSnackTitle.visibility = View.GONE
                    binding.tvBreakfastTitle.text = "Dishes"
                    binding.ivCollapseBreakfast.setImageResource(R.drawable.close_eye)
                    hiddenStateBreakfast = true
                    binding.tvDinnerDescription.visibility = View.GONE
                    binding.tvBreakfastDescription.visibility = View.GONE
                    binding.tvLunchDescription.visibility = View.GONE
                    binding.tvSnackDescription.visibility = View.GONE
                }
            }
            R.id.ivMinus -> {
                planViewModel.ingredientList.forEach { it.selected = false }
                adapterIngredient?.updateData(planViewModel.ingredientList)
                var current: Int = binding.tvNumberServe.text.toString().toInt()
                if (current == 1) {
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

    private fun checkTotal() {
        val totalSize =
            planViewModel.lunch.size + planViewModel.dinner.size + planViewModel.snack.size + planViewModel.morning.size
        if (totalSize == 0) {
            binding.rvIngredients.visibility = View.GONE
            binding.tvTotalDescription.visibility = View.VISIBLE
        } else {
            binding.rvIngredients.visibility = View.VISIBLE
            binding.tvTotalDescription.visibility = View.GONE
        }
    }

    private fun showWarning(content: String) {
        val textValue = HtmlCompat.fromHtml("<b>Warning</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        val textButton =
            HtmlCompat.fromHtml("<font color='#FF9C00'>OK</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
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

    private fun updateIngredients() {
        planViewModel.ingredientList.removeFirst()
        planViewModel.ingredientList2.removeFirst()
        adapterIngredient?.updateData(planViewModel.ingredientList)
    }
}