package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.R.drawable.ic_salad
import com.example.foodlife.models.MainCategoryModel
import com.example.foodlife.models.Recipe

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var recList = MutableLiveData<List<Recipe>>().also {
        it.value = listOf()
    }
    var mainList = MutableLiveData<List<MainCategoryModel>>().also {
        it.value = listOf()
    }
    var collectionList = MutableLiveData<List<Recipe>>().also {
        it.value = listOf()
    }

    fun loadRecommend(){
        val exampleList = listOf<Recipe>(
            Recipe(R.drawable.recommend_1, "Stir-fried beef with broccoli and Rice", 3.5F,"",0,""),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 3.5F,"",0,"" ),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 3.5F,"",0,"" ),
        )
        recList.value = exampleList
    }
    fun loadMainList(){
        val exampleList = listOf<MainCategoryModel>(
            MainCategoryModel(R.drawable.ic_main_cat_asian, "Asian"),
            MainCategoryModel(R.drawable.ic_western,"Western"),
            MainCategoryModel(R.drawable.ic_drinks,"Drinks"),
            MainCategoryModel(R.drawable.ic_dessert,"Dessert"),
            MainCategoryModel(ic_salad,"Salad"),
            MainCategoryModel(R.drawable.ic_party,"Party"),
        )
        mainList.value = exampleList
    }
    fun loadCollection(){
        val exampleList = listOf<Recipe>(
            Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 3.5F,"",0,"Cheese, Tomato, egg,..."),
            Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 3.5F,"",0,"Oat, Milk, Strawberry, Banana,..." ),
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes", 3.5F,"",0,"Cheese, Honey, Blueberry, ..." ),
        )
        collectionList.value = exampleList
    }
}