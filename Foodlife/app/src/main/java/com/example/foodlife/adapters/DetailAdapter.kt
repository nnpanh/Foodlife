package com.example.foodlife.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodlife.fragments.DetailDirectionsFragment
import com.example.foodlife.fragments.DetailFragment
import com.example.foodlife.fragments.DetailIngredientsFragment
import com.example.foodlife.fragments.DetailReviewFragment

class DetailAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> return DetailIngredientsFragment()
            1 -> return DetailDirectionsFragment()
            2 -> return DetailReviewFragment()
            else -> return DetailIngredientsFragment()
        }
    }

}