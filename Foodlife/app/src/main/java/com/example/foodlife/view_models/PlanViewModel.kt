package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.PlanImageModel
import com.example.foodlife.models.PlanTextModel
import com.example.foodlife.models.UserModel

class PlanViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is plan Fragment"
    }
    val text: LiveData<String> = _text
    var breakfastList = MutableLiveData<List<PlanTextModel>>().also {
        it.value = listOf()
    }
    var lunchList = MutableLiveData<List<PlanTextModel>>().also {
        it.value = listOf()
    }

    var snackList = MutableLiveData<List<PlanTextModel>>().also {
        it.value = listOf()
    }

    var breakfastImageList = MutableLiveData<List<PlanImageModel>>().also {
        it.value = listOf(
            PlanImageModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium"),
            PlanImageModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy"),
            PlanImageModel("Bún bò Huế","4.5",  R.drawable.example_dish_3,"60 min","Hard"),
        )
    }
    var lunchImageList = MutableLiveData<List<PlanImageModel>>().also {
        it.value = listOf(
            PlanImageModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium"),
            PlanImageModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy"),

            )
    }

    var snackImageList = MutableLiveData<List<PlanImageModel>>().also {
        it.value = listOf(
            PlanImageModel("Japanese Ramen","4.5",  R.drawable.example_dish_1,"60 min","Medium"),
            PlanImageModel("Spicy sauce Tokbokki","4,5",  R.drawable.example_dish_2,"30 min","Easy"),
            PlanImageModel("Bún bò Huế","4.5",  R.drawable.example_dish_3,"60 min","Hard"),

            )
    }

    var viewMode = true //true = text, false = image

    fun loadBreakFast(){
        val exampleList = listOf<PlanTextModel>(
            PlanTextModel("Japanese Ramen", "NNPAnh", R.drawable.example_dish_1),
            PlanTextModel("Spicy sauce Tokbokki", "NTTNga", R.drawable.example_dish_2),
            PlanTextModel("Bún bò Huế", "NPVy21", R.drawable.example_dish_3),
        )
        breakfastList.value = exampleList

    }

    fun loadLunch(){
        val exampleList = listOf<PlanTextModel>(
            PlanTextModel("Japanese Ramen", "NNPAnh", R.drawable.example_dish_1),
            PlanTextModel("Spicy sauce Tokbokki", "NTTNga", R.drawable.example_dish_2),
        )
        lunchList.value = exampleList
    }

    fun loadSnack(){
        val exampleList = listOf<PlanTextModel>(
            PlanTextModel("Japanese Ramen", "NNPAnh", R.drawable.example_dish_1),
            PlanTextModel("Spicy sauce Tokbokki", "NTTNga", R.drawable.example_dish_2),
            PlanTextModel("Bún bò Huế", "NPVy21", R.drawable.example_dish_3),
        )
        snackList.value = exampleList

    }
}