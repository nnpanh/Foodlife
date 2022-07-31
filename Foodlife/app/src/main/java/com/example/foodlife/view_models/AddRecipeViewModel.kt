package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.models.AddRecipe
import com.example.foodlife.models.AddRecipeDirectionModel
import com.example.foodlife.models.AddRecipeIngredientModel
import com.example.foodlife.models.Collection

class AddRecipeViewModel : ViewModel() {
    var initIngredient = mutableListOf<AddRecipeIngredientModel>(
        AddRecipeIngredientModel("Ingredient", "1", "g")
    )

    var initDirection = mutableListOf<AddRecipeDirectionModel>(
        AddRecipeDirectionModel("Direction", 1, null)
    )

    private var _text = MutableLiveData<String>().apply { value = "This is Add Recipe Fragment" }

    val text: LiveData<String> = _text
    var recipeList = MutableLiveData<MutableList<AddRecipe>>().also {
        it.value = mutableListOf()
    }

    fun addRecipe(recipe: AddRecipe){
        recipeList.value!!.add(recipe)
    }


}