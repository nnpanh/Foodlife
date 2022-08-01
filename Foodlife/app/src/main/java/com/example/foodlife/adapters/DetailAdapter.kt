package com.example.foodlife.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodlife.fragments.DetailDirectionsFragment
import com.example.foodlife.fragments.DetailFragment
import com.example.foodlife.fragments.DetailIngredientsFragment
import com.example.foodlife.fragments.DetailReviewFragment
import com.example.foodlife.models.AddRecipe

class DetailAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    private var recipe: AddRecipe? = null

    public fun setRecipe(recipe: AddRecipe){
        this.recipe = recipe
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> DetailIngredientsFragment(this.recipe)
            1 -> DetailDirectionsFragment(this.recipe)
            2 -> DetailReviewFragment(this.recipe)
            else -> DetailIngredientsFragment(this.recipe)
        }
    }

}