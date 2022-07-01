package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodlife.R
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel


class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val planViewModel =
            ViewModelProvider(this)[PlanViewModel::class.java]

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

        val testUser = UserModel(0, "PA", R.drawable.catcool, 23, null, null)
        val directUser = UserEntity( "Hehe", R.drawable.catcool, 23, null, null)
        val database = activity?.let { FoodlifeDB.getInstance(it.applicationContext) }
        database?.clearAllTables()
        database?.userDAO()?.insert(directUser)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}