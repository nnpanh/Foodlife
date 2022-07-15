package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.models.AddRecipeIngredientModel

class AddRecipeViewModel : ViewModel() {
    var initIngredient = mutableListOf<AddRecipeIngredientModel>(
        AddRecipeIngredientModel("Ingredient", 1)
    )
}