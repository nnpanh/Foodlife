package com.example.foodlife.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.AddRecipeDirectionAdapter
import com.example.foodlife.adapters.AddRecipeIngredientAdapter
import com.example.foodlife.databinding.FragmentAddRecipeDirectionsBinding
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.AddRecipeIngredientModel
import com.example.foodlife.view_models.AddRecipeViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeDirectionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeDirectionsFragment : Fragment(), View.OnClickListener {
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
//        return inflater.inflate(R.layout.fragment_add_recipe_directions, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AddRecipeDirectionsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AddRecipeDirectionsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeDirectionsBinding? = null
    private val binding get() = _binding!!

    private var adapterDirection: AddRecipeDirectionAdapter? = null

    private lateinit var directionViewModel: AddRecipeViewModel

    private var mList: MutableList<AddRecipeDirectionModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeDirectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        directionViewModel = ViewModelProvider(store)[AddRecipeViewModel::class.java]
        mList = directionViewModel.initDirection
        initListener()
        initAdapters()
    }

    private fun initListener() {
        binding.addBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.arAddDirectionBtn.setOnClickListener(this)
    }

    private fun initAdapters(){
        if (adapterDirection == null){
            adapterDirection = AddRecipeDirectionAdapter (){clickedItem ->
//                val updateList = ingredientViewModel.initIngredient
//                val list = adapterIngredient.m
                if (mList.size>1){
                    mList.remove(clickedItem)
                    Log.e("Direction", "removed")
                    adapterDirection!!.updateData(mList)
                }
            }
            binding.rcvDirectionList.adapter = adapterDirection
            binding.rcvDirectionList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapterDirection!!.updateData(directionViewModel.initDirection)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.add_btn -> {
                navController.navigate(R.id.returnHome)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
            R.id.ar_add_direction_btn ->{
                val item = AddRecipeDirectionModel("Direction", mList.size + 1)
                mList.add(item)
                Log.i("số lượng: ", mList.size.toString())
                adapterDirection!!.updateData(mList)
            }
        }
    }
}