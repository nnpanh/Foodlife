package com.example.foodlife.fragments

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding

class AddRecipeInformationFragment : Fragment() {

//    companion object {
//        fun newInstance() = AddRecipeFragment()
//    }
//
//    private lateinit var viewModel: AddRecipeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        return inflater.inflate(R.layout.fragment_addrecipe_tittle, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(AddRecipeViewModel::class.java)
//        // TODO: Use the ViewModel
//    }
    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddRecipeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}