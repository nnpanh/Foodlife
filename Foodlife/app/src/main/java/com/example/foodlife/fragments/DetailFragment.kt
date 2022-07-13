package com.example.foodlife.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import com.example.foodlife.R
import com.example.foodlife.adapters.DetailAdapter
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.dialog.AddToPlanBottomDialog
import com.example.foodlife.dialog.OptionBottomDialog
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isSpinnerInitial = true

    private var bottomDialog = OptionBottomDialog()

    var tabTitle = arrayOf("Ingredients", "Directions", "Review")

    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Viewpager & TabLayout
        var pager = binding.viewPager2
        var tl = binding.tabLayout
        pager.adapter = DetailAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tl,pager){
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()
        initListener()
    }

    private fun initListener(){
        binding.detailMenu.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.detail_menu -> {
//                DetailMoreMenu().show(childFragmentManager, DetailMoreMenu.TAG)
                bottomDialog.show(parentFragmentManager, OptionBottomDialog.TAG)
                bottomDialog.setFragmentResultListener("key") { _ ,bundle ->
                    bundle.getString("Action")?.let {
                        when(it){
                            "Save to collection" -> {
                                saveToCollection()
                            }
                            "Share to other platforms" -> {

                            }
                            "Add to my plan" -> {
                                addToPlan()

                            }
                        }
                    }
                }
            }
        }
    }
    private fun addToPlan(){
        val addToPlanBottomDialog = AddToPlanBottomDialog()
        addToPlanBottomDialog.show(parentFragmentManager, AddToPlanBottomDialog.TAG)
        addToPlanBottomDialog.setFragmentResultListener("result"){ _,bundle ->

        }
    }

    private fun saveToCollection() {

    }
}
