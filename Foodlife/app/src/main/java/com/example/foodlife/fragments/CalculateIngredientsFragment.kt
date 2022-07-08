package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.PlanImageAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.adapters.PlanTotalAdapter
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel


class CalculateIngredientsFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentCalculateIngredientsBinding? = null
    private val binding get() = _binding!!

    private var adapterTotalDishes: PlanTotalAdapter? = null

    private lateinit var planViewModel: PlanViewModel
    private var hiddenState = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateIngredientsBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
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
        initTotalAdapter()

    }

    private fun initListener() {
        binding.ivBack.setOnClickListener(this)
        binding.ivCollapse.setOnClickListener(this)

    }

    private fun initTotalAdapter() {
        //Create adapter
        if (adapterTotalDishes == null) {
            adapterTotalDishes = PlanTotalAdapter()
        }
        adapterTotalDishes!!.updateData(planViewModel.totalList)
        if (planViewModel.totalList.size == 0) binding.tvTotalDescription.visibility = View.VISIBLE

            //Set adapter to recyclerView
        binding.rvTotalDishes.apply {
                adapter = adapterTotalDishes
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
            R.id.ivCollapse -> {
                if (hiddenState) {
                    binding.rvTotalDishes.visibility = View.VISIBLE
                    hiddenState = false
                }
                else {
                    binding.rvTotalDishes.visibility = View.GONE
                    hiddenState = true
                }
            }
        }
    }
}