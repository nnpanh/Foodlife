package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.AddRecipeIngredientModel

class AddRecipeViewModel : ViewModel() {
    var initIngredient = mutableListOf<AddRecipeIngredientModel>(
        AddRecipeIngredientModel("Ingredient", "1", "g")
    )

    var initDirection = mutableListOf<AddRecipeDirectionModel>(
        AddRecipeDirectionModel("Direction", 1, null)
    )
}