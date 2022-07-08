package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.MainCategoryModel
import com.example.foodlife.models.Recipe

class RecommendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var recList = MutableLiveData<List<Recipe>>().also {
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

}