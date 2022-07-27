package com.example.foodlife.view_models

import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.models.PlanWithTypeModel
import com.example.foodlife.models.Recipe

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

    var dinnerList = mutableListOf<PlanItemModel>(
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

    var morning = mutableListOf(
        Recipe(R.drawable.ivspring,"Spring potato and asparagus salad", 5,117,"Easy",60,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_roasted_salad, "Roasted persimmon salad with maple-mustard vinaigrette", 5,117,"",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.ivredavocado, "Red cabbage with grilled avocado", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
    )

    var lunch = mutableListOf(
        Recipe(R.drawable.souffle_omelette, "Souffle omelette", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
    )

    var dinner = mutableListOf<Recipe>(
    )

    var snack = mutableListOf(
        Recipe(R.drawable.jam_donut, "Jam doughnuts", 4, 117,"Easy", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
        Recipe(R.drawable.ivclassicapplestrudel, "Classic apple strudel", 4, 117,"Easy", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
        Recipe(R.drawable.ivdumplings, "Sweet plum dumplings", 4, 117,"Easy", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
    )
}