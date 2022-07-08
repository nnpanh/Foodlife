package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.models.PlanWithTypeModel

class PlanViewModel : ViewModel() {
    var breakfastList = mutableListOf(
            PlanItemModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium","NTTNga"),
            PlanItemModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy","NTTNga"),
            PlanItemModel("Bún bò Huế","4.5",  R.drawable.example_dish_3,"60 min","Hard","NNPAnh"),
        )

    var lunchList = mutableListOf(
            PlanItemModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium","NNPAnh"),
            PlanItemModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy","NNPAnh"),

            )

    var snackList = mutableListOf(
            PlanItemModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium","NPVy21"),
            PlanItemModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy","NTTNga"),
            PlanItemModel("Bún bò Huế","4.5",  R.drawable.example_dish_3,"60 min","Hard","NTTNga"),
            )

    var totalList = mutableListOf(
        PlanWithTypeModel("Japanese Ramen",  R.drawable.example_dish_1,"NTTNga",0),
        PlanWithTypeModel("Spicy sauce Tokbokki",  R.drawable.example_dish_2,"NTTNga",0),
        PlanWithTypeModel("Bún bò Huế",  R.drawable.example_dish_3,"NNPAnh",0),
        PlanWithTypeModel("Japanese Ramen",  R.drawable.example_dish_1,"NNPAnh",1),
        PlanWithTypeModel("Spicy sauce Tokbokki",  R.drawable.example_dish_2,"NNPAnh",1),
        PlanWithTypeModel("Japanese Ramen",  R.drawable.example_dish_1,"NPVy21",3),
        PlanWithTypeModel("Spicy sauce Tokbokki",  R.drawable.example_dish_2,"NTTNga",3),
        PlanWithTypeModel("Bún bò Huế",  R.drawable.example_dish_3,"NTTNga",3),
    )

    var ingredientList = mutableListOf(
        Ingredient("peeled boiled egg","6"),
        Ingredient("mayonnaise","1/4 cup"),
        Ingredient("lemon juice","1 tbsp"),
        Ingredient("salt","1/4 tbsp"),
        Ingredient("pepper","1/4 tbsp"),
        Ingredient("chopped celery","60g"),
        Ingredient("green onions","18g sliced"),
        Ingredient("wheat bread with grapefruits","8 slices"),
        Ingredient("lettuce leaves","4"),
    )

    var ingredientList2 = mutableListOf(
        Ingredient("peeled boiled egg","12"),
        Ingredient("mayonnaise","1/2 cup"),
        Ingredient("lemon juice","2 tbsp"),
        Ingredient("salt","1/2 tbsp"),
        Ingredient("pepper","1/2 tbsp"),
        Ingredient("chopped celery","120g"),
        Ingredient("green onions","36g sliced"),
        Ingredient("wheat bread with grapefruits","16 slices"),
        Ingredient("lettuce leaves","8"),
    )


    var viewMode = true //true = text, false = image
}