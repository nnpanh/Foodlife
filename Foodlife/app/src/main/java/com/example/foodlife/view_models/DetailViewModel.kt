package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.DetailDirections
import com.example.foodlife.models.DetailIngredients

class DetailViewModel: ViewModel() {

     var DetailIngredientsList = mutableListOf(
         DetailIngredients("peeled boiled egg","6"),
         DetailIngredients("mayonnaise","1/4 cup"),
         DetailIngredients("lemon juice","1 tbsp"),
         DetailIngredients("salt","1/4 tbsp"),
         DetailIngredients("pepper","1/4 tbsp"),
         DetailIngredients("chopped celery","60g"),
         DetailIngredients("green onions","18g sliced"),
         DetailIngredients("wheat bread with grapefruits","8 slices"),
         DetailIngredients("lettuce leaves","4"),
     )

    var DetailIngredientsList2 = mutableListOf(
        DetailIngredients("peeled boiled egg","12"),
        DetailIngredients("mayonnaise","1/2 cup"),
        DetailIngredients("lemon juice","2 tbsp"),
        DetailIngredients("salt","1/2 tbsp"),
        DetailIngredients("pepper","1/2 tbsp"),
        DetailIngredients("chopped celery","120g"),
        DetailIngredients("green onions","36g sliced"),
        DetailIngredients("wheat bread with grapefruits","16 slices"),
        DetailIngredients("lettuce leaves","8"),
    )

    var DetailDirectionsList = mutableListOf(
        DetailDirections(R.drawable.example_step_1,"Chop eggs.","Step 1:"),
        DetailDirections(R.drawable.example_step_2,"Mix mayonnaise, lemon juice, mustard, salt and pepper in medium bowl.","Step 2:"),
    )
}