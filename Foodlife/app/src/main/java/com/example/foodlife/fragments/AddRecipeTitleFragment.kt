package com.example.foodlife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentAddRecipeInformationBinding
import com.example.foodlife.databinding.FragmentAddRecipeTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeTitleFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var navController: NavController
    private var _binding: FragmentAddRecipeTitleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddRecipeTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    //    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AddRecipeTitleFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AddRecipeTitleFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        initListener()
    }

    private fun initListener() {
        binding.nextBtn.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.next_btn -> {
                navController.navigate(R.id.addRecipeTittleFragment_to_addRecipeInformationFragment)
                //TODO
            }
            R.id.ivBack -> {
                navController.navigateUp()
                //TODO
            }
        }
    }
}