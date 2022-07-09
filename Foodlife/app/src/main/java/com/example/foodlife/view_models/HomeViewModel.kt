package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.R.drawable.ic_salad
import com.example.foodlife.models.MainCategoryModel
import com.example.foodlife.models.Recipe
import com.example.foodlife.models.RecommendCategoryModel

class HomeViewModel : ViewModel() {

    var recList = mutableListOf(
        Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", "5","Medium","45 mins","Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
        Recipe(R.drawable.salted,"Salted Caramel Granola", "5","Easy","15 mins","Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
        Recipe(R.drawable.rec2,"Potato Noodle","5","Medium","45 mins","Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
        Recipe(R.drawable.example_dish_3,"Bún bò Huế", "5","Difficult","120 mins","",R.drawable.profile3,"Thanh Lam" ),
    )
   var mainList = mutableListOf(
       MainCategoryModel(R.drawable.ic_main_cat_asian, "Asian"),
       MainCategoryModel(R.drawable.ic_western,"Western"),
       MainCategoryModel(R.drawable.ic_drinks,"Drinks"),
       MainCategoryModel(R.drawable.ic_dessert,"Dessert"),
       MainCategoryModel(ic_salad,"Salad"),
       MainCategoryModel(R.drawable.ic_party,"Party"),
   )
    var collectionList = mutableListOf(
        Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", "3.5","","0","Cheese, Tomato, egg,...",0,""),
        Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", "3.5","","0","Oat, Milk, Strawberry, Banana,..." ,0,""),
        Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes","3.5","","0","Cheese, Honey, Blueberry, ...",0,"" ),
    )

    var recCat= mutableListOf(
        RecommendCategoryModel("Meat Chop"),
        RecommendCategoryModel("Vegetables"),
        RecommendCategoryModel("Korea"),
        RecommendCategoryModel("Vietnamese"),
        RecommendCategoryModel("Dessert"),
    )
}