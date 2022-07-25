package com.example.foodlife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddRecipeIngredientAdapter
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.databinding.FragmentAddRecipeIngredientsBinding
import com.example.foodlife.models.AddRecipeIngredientModel
import com.example.foodlife.view_models.AddRecipeViewModel

class AddRecipeIngredientsFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeIngredientsBinding? = null

    private val binding get() = _binding!!

    private var adapterIngredient: AddRecipeIngredientAdapter? = null

    private lateinit var ingredientViewModel: AddRecipeViewModel

    private var mList: MutableList<AddRecipeIngredientModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        ingredientViewModel = ViewModelProvider(store)[AddRecipeViewModel::class.java]
        mList = ingredientViewModel.initIngredient
        initListener()
        initAdapters()

    }

    private fun initListener() {
        binding.continueBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.arAddIngredientBtn.setOnClickListener(this)
    }

    private fun initAdapters(){
        if (adapterIngredient == null){
            adapterIngredient = AddRecipeIngredientAdapter(requireActivity()){clickedItem ->
                Log.e("Ingredient", "removed")
                Log.e("Ingredient size", mList.size.toString());
                if (mList.size>1){
                    mList.remove(clickedItem)

                    adapterIngredient!!.updateData(mList)
                }
            }
        }
        /*binding.rcvIngredientList.adapter = adapterIngredient
        binding.rcvIngredientList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapterIngredient!!.updateData(ingredientViewModel.initIngredient)*/
        setAdapter(adapterIngredient!!, binding.rcvIngredientList)
        ingredientViewModel.initIngredient.let{ adapterIngredient!!.updateData(it)}
    }

    private fun setAdapter(_adapter: AddRecipeIngredientAdapter, _recyclerView: RecyclerView) {
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

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.continue_btn -> {
                navController.navigate(R.id.addRecipeIngredientFragment_to_addRecipeDirectionsFragment)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ar_add_ingredient_btn -> {
                val item = AddRecipeIngredientModel("Ingredient", "1", "g")
//                adapterIngredient?.addData(item)
//                mList = adapterIngredient!!.getList()
                /*val newList = adapterIngredient?.mList!!.toMutableList()
                newList.add(item)
                mList = newList*/
                mList.add(item)
                adapterIngredient!!.updateData(mList)
//                adapterIngredient!!.updateData(mList)
            }
        }
    }

}