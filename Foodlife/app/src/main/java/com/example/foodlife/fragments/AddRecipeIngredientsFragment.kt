package com.example.foodlife.fragments

import android.os.Bundle
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
import com.example.foodlife.databinding.FragmentAddRecipeIngredientsBinding
import com.example.foodlife.databinding.FragmentCalculateIngredientsBinding
import com.example.foodlife.models.AddRecipeIngredientModel
import com.example.foodlife.view_models.AddRecipeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeIngredientsFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_recipe_ingredients, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AddRecipeIngredientsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AddRecipeIngredientsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeIngredientsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        binding.nextBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.arAddIngredientBtn.setOnClickListener(this)
    }

    private fun initAdapters(){
        if (adapterIngredient == null){
            adapterIngredient = AddRecipeIngredientAdapter(requireActivity()){clickedItem ->
//                val updateList = ingredientViewModel.initIngredient
//                val list = adapterIngredient.m
                if (mList.size>1){
                    mList.remove(clickedItem)
                    adapterIngredient!!.updateData(mList)
                }
            }
            binding.rcvIngredientList.adapter = adapterIngredient
            binding.rcvIngredientList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapterIngredient!!.updateData(ingredientViewModel.initIngredient)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.next_btn -> {
                navController.navigate(R.id.addRecipeIngredientFragment_to_addRecipeDirectionsFragment)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ar_add_ingredient_btn -> {
                val item = AddRecipeIngredientModel("Ingredient", 1)
                mList.add(item)
                adapterIngredient!!.updateData(mList)
            }
        }
    }

}