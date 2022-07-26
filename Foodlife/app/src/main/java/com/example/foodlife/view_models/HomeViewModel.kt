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
        Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),
        Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna",""),
        Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam",""),
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
        Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 4,"Medium",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria",""),
        Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna",""),
        Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna",""),
    )

    var recCat= mutableListOf(
        RecommendCategoryModel("Meat Chop"),
        RecommendCategoryModel("Vegetables"),
        RecommendCategoryModel("Western"),
        RecommendCategoryModel("Vietnamese"),
        RecommendCategoryModel("Dessert"),
    )

    var searchList = /*MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadSearchList() {
        val list =*/ mutableListOf(
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.jam_donut, "Jam doughnuts", 4, "Easy", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 4,"Medium",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"",R.drawable.profile3,"Thanh Lam",""),
            Recipe(R.drawable.img_pngegg,"Bánh gạo cay", 3,"Easy",45,"Difficult",R.drawable.profile3,"Thanh Lam",""),
            Recipe(R.drawable.img_tok, "Bánh gạo cay", 4,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise",""),
            Recipe(R.drawable.img_pho, "Phở bò", 3,"Difficult",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise",""),
        )
       /* searchList.value = list
    }*/

    //Category List
    var meatList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadMeatList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),

            Recipe(R.drawable.example_dish_3,"Bún bò Huế", 5,"Difficult",120,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Thanh Lam" ,""),
            Recipe(R.drawable.img_pngegg,"Mì thịt xào Nhật", 3,"Easy",45,"Enjoy each your special dinner",R.drawable.profile3,"Thanh Lam","" ),
            )
        meatList.value = list
    }

    var vegetableList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVegetableList() {
        val list = mutableListOf(
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ,""),
        )
        vegetableList.value = list
    }

    var westernList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadWesternList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.rec2,"Potato Noodle",5,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ,""),
            Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 4,"Medium",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,""),
            )
        westernList.value = list
    }

    var vietnamList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVietNamList() {
        val list = mutableListOf(
            Recipe(R.drawable.example_dish_3, "Bún bò Huế", 5,"Difficult",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.img_pho, "Phở bò", 3,"Difficult",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise",""),
            )
        vietnamList.value = list
    }

    var dessertList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadDessertList() {
        val list = mutableListOf(
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,""),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.jam_donut, "Jam doughnuts", 4, "Easy", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
            )
        dessertList.value = list
    }

    var saveList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadSaveList() {
        val list = mutableListOf(
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,""),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria",""),

            )
        saveList.value = list
    }



    //Filter
    var resultFilterList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun FilterSearch(catCondition :MutableList<String>): MutableList<Recipe> {
        var filterList = mutableListOf<Recipe>()
        //var filterList: MutableList<Recipe>? =null

        if(catCondition.size==2 && catCondition[0]=="false" && catCondition[1]=="false"){
            filterList.addAll(searchList)
            return filterList
        }
        //1. no category --> filterList = searchList
        //   has category--> size>2 --> load list to filterList

        if (catCondition.size>2) {
                //filterList.clear()
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

        //2. time==false --> nothing
        //   time==true --> remove row with condition from filterList
        if (catCondition[0]!="false"){
            if(filterList.size==0){
                filterList.addAll(searchList)
            }
            var tempList=filterList
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
            if(filterList.size==0){
                filterList.addAll(searchList)
            }
            val resultList = ArrayList<Recipe>()
            for(row in filterList){
                //resultList.add(row)
                if(catCondition[1].contains(row.score.toString()))
                    resultList.add(row)
            }
            filterList=resultList
            /*if (resultList.size>0)
                filterList=resultList
            else if(resultList.size==0){
                //filterList=dessertList.value!!
            }*/
        }


        return filterList
    }
}