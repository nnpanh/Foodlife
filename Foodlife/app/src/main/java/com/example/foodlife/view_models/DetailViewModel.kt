package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.DetailDirections
import com.example.foodlife.models.DetailIngredients
import com.example.foodlife.models.DetailReview

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
        DetailDirections(R.drawable.example_step_3,"Mix mayonnaise, lemon juice, mustard, salt and pepper in medium bowl.","Step 2:"),
        DetailDirections(R.drawable.example_step_2,"Serves in to plate and enjoy your delicious meal.","Step 3:"),
    )

    var DetailReviewList = mutableListOf(
        DetailReview(R.drawable.example_step_1,"Melanie Rose","Lorem ipsum dolor sit amet, consectetur adipiscing elit!"),
        DetailReview(R.drawable.example_step_2,"Jonathan Jose","Excepteur sint occaecat cupidatat non proident, sunt"),
        DetailReview(R.drawable.ic_user,"Nicky","nisi ut aliquip ex ea commodo consequat."),
        DetailReview(R.drawable.ic_user,"Moon Star","quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea."),
        DetailReview(R.drawable.ic_user,"Melanie","dolor in reprehenderit in voluptate velit esse cillum dolore eu."),
    )
}