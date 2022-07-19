package com.example.foodlife.models

data class AddRecipeIngredientModel (
    var name: String,
    var quantity: String,
    var measure: String
        ){
    var selectedPosition = 0
}