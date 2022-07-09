package com.example.foodlife.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.databinding.FragmentProfileBinding
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.view_models.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        val database = activity?.applicationContext?.let { FoodlifeDB.getInstance(it) }

        val getUser =  database?.userDAO()?.findByName("Hehe")
        Log.d("User:", "${getUser?.name}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}