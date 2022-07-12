package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.R.drawable.ic_salad
import com.example.foodlife.models.Collection
import com.example.foodlife.models.MainCategoryModel
import com.example.foodlife.models.Recipe
import com.example.foodlife.models.RecommendCategoryModel

class HomeViewModel : ViewModel() {

    var recList = mutableListOf(
        Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
        Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
        Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
        Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam" ),
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
        Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 5,"",0,"Cheese, Tomato, egg,...",0,""),
        Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,"",0,"Oat, Milk, Strawberry, Banana,..." ,0,""),
        Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",5,"",0,"Cheese, Honey, Blueberry, ...",0,"" ),
    )

    var recCat= mutableListOf(
        RecommendCategoryModel("Meat Chop"),
        RecommendCategoryModel("Vegetables"),
        RecommendCategoryModel("Korea"),
        RecommendCategoryModel("Vietnamese"),
        RecommendCategoryModel("Dessert"),
    )

    var searchList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadSearchList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam" ),

        )
        searchList.value = list
    }

    //Category List
    var meatList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadMeatList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Meat", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam" ),
            )
        meatList.value = list
        //resultFilterList.value!!.addAll(list)
    }

    var vegetableList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVegetableList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Vegetable", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam" ),
            )
        vegetableList.value = list
    }

    var westernList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadWesternList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "western", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam" ),
            )
        westernList.value = list
    }

    var vietnamList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVietNamList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "VN", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise"),

            )
        vietnamList.value = list
    }

    var dessertList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadDessertList() {
        val list = mutableListOf(

            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria"),

            )
        dessertList.value = list
    }



    //Filter
    var resultFilterList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun FilterSearch(catCondition :MutableList<String>){

        var filterList: MutableList<Recipe> = searchList.value!!
        var resultList: MutableList<Recipe> = searchList.value!!

        //1. catState==false --> filterList = searchList

        if (catCondition.size>3)
        //   catState == true --> load list to filterList
        {
            filterList.clear()
            if(catCondition.contains("Meat")){
                filterList.addAll(meatList.value!!)
            }
            if(catCondition.contains("Vegetables")){
                filterList.addAll(vegetableList.value!!)
            }
            if(catCondition.contains("Vietnamese")){
                filterList.addAll(vietnamList.value!!)
            }
            if(catCondition.contains("Western")){
                filterList.addAll(westernList.value!!)
            }
            if(catCondition.contains("Dessert")){
                filterList.addAll(dessertList.value!!)
            }
        }
        var tempList=filterList
        //2. time==false --> nothing
        //   time==true --> remove row with condition from filterList
        if (catCondition[0]!="false"){
            for(row in tempList){
                if(catCondition[0]=="Within 30 mins")
                    filterList=filterList.filter { it.time<=30 }.toMutableList()
                else if(catCondition[0]=="Within 1 hours")
                    filterList=filterList.filter { it.time <=60 }.toMutableList()
                else if(catCondition[0]=="More")
                    filterList=filterList.filter { it.time>60 }.toMutableList()
            }
        }
        //3. rate==false --> resultList = filterList
        //   rate==true -->  add row to resultList with condition from filterList
        if(catCondition[1]!="false"){
            resultList.clear()

        }

        resultFilterList.value=filterList
    }
}