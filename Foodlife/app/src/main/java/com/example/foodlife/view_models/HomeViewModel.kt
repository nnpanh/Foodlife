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
        Recipe(R.drawable.col3_rep1,"One-Pot Tomato Basil Pasta", 5,117,"Easy",60,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.col3_rep2, "Veggie & Hummus Sandwich", 5,117,"",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_roasted_chicken, "Roasted chicken in honey-ginger-soy-sauce", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.salted,"Salted Caramel Granola", 5,117,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
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
        Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 4,117,"Medium",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,117,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iveasylemongrassmeatballs, "Easy lemongrass meatballs", 4,117,"Medium",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.ivchickenwings,"Simple marinated chicken wings", 5,117,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
    )

    var recentlyList = mutableListOf(
        Recipe(R.drawable.col2_rep1,"Scout's lobster rolls",5,117,"Medium",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.col2_rep2,"Pasta puttanesca", 4,117,"Easy",30,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Thanh Lam","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.col2_rep3, "Korean fish pancakes", 5,117,"Difficult",25,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
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

        //Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),

        Recipe(R.drawable.iv_spicy_pa, "Spicy pan-fried chickpeas with beef", 5,157,"Medium",60,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.ivsteak, "Flank steak with Chimichurri", 5,117,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iv_buttermilkchickenwings, "Buttermilk chicken wings", 5,117,"Hard",190,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        //Recipe(R.drawable.roasted_lamb_shoulder, "Roasted lamp shoulder", 5,200,"Hard",180,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iv_borscht, "Borscht", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iv_thai, "Thai chicken and sweet potato curry", 4,117,"Medium",60,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iv_roasted_chicken, "Roasted chicken in honey-ginger-soy-sauce", 4,117,"Hard",190,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iveasylemongrassmeatballs, "Easy lemongrass meatballs", 4,200,"Hard",180,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.col3_rep1,"One-Pot Tomato Basil Pasta", 5,400,"Easy",120,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.col3_rep2, "Veggie & Hummus Sandwich", 5,500,"Easy",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.souffle_omelette, "Souffle omelette", 5,410,"Medium",20,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.salted,"Salted Caramel Granola", 5,117,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.rec2,"Potato Noodle",5,117,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.ivspring,"Spring potato and asparagus salad", 4,310,"Easy",30,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_roasted_salad, "Roasted persimmon salad with maple-mustard vinaigrette", 4,117,"Medium",30,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.ivredavocado, "Red cabbage with grilled avocado", 3,11,"Medium",30,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
        Recipe(R.drawable.iv_spaghetti,"Spaghetti aglio e olio with broccoli", 3,10,"Medium",45,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 5,200,"Easy",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,117,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.cauliflower_cheese,"Cauliflower cheese",3,10,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_banhmi, "Beef banh mi with fresh cilantro", 5,118,"Easy",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_noodle, "Tomato and egg noodle soup", 5,1000,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_braisedlargeshrimp, "Braised large shrimp", 5,500,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_c, "Vietnamese inspired noodle soup", 4,117,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.jam_donut, "Jam doughnuts", 5, 300,"Medium", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
        Recipe(R.drawable.ivclassicapplestrudel, "Classic apple strudel", 4, 117,"Hard", 60, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
        Recipe(R.drawable.ivdumplings, "Sweet plum dumplings", 4, 210,"Medium", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
        Recipe(R.drawable.ivsuper,"Superfood green smoothie",5,117,"Easy",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_workout,"Post-workout smoothie", 4,10,"Easy",15,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Thanh Lam","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        //Recipe(R.drawable.iv_blue_smoothie, "Pomegranate blueberry smoothie", 3,11,"Easy",10,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.jam_tarts,"Jam Tarts",5,200,"Easy",30,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.strawberryjam, "Strawberry Jam", 4,110,"Medium",25,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        )
       /* searchList.value = list
    }*/

    //Category List
    var meatList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadMeatList() {
        val list = mutableListOf(
            Recipe(R.drawable.iv_spicy_pa, "Spicy pan-fried chickpeas with beef", 5,157,"Medium",60,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.ivsteak, "Flank steak with Chimichurri", 5,117,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iv_buttermilkchickenwings, "Buttermilk chicken wings", 5,117,"Hard",190,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            //Recipe(R.drawable.roasted_lamb_shoulder, "Roasted lamp shoulder", 5,200,"Hard",180,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iv_borscht, "Borscht", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iv_thai, "Thai chicken and sweet potato curry", 4,117,"Medium",60,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iv_roasted_chicken, "Roasted chicken in honey-ginger-soy-sauce", 4,117,"Hard",190,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iveasylemongrassmeatballs, "Easy lemongrass meatballs", 4,200,"Hard",180,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            )
        meatList.value = list
    }

    var vegetableList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVegetableList() {
        val list = mutableListOf(
            Recipe(R.drawable.col3_rep1,"One-Pot Tomato Basil Pasta", 5,400,"Easy",120,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.col3_rep2, "Veggie & Hummus Sandwich", 5,500,"Easy",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.souffle_omelette, "Souffle omelette", 5,410,"Medium",20,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,117,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,117,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.ivspring,"Spring potato and asparagus salad", 4,310,"Easy",30,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.iv_roasted_salad, "Roasted persimmon salad with maple-mustard vinaigrette", 4,117,"Medium",30,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.ivredavocado, "Red cabbage with grilled avocado", 3,11,"Medium",30,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.iv_spaghetti,"Spaghetti aglio e olio with broccoli", 3,10,"Medium",45,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        )
        vegetableList.value = list
    }

    var westernList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadWesternList() {
        val list = mutableListOf(
            Recipe(R.drawable.rec1, "Stir-fried beef with broccoli and Rice", 5,117,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile2,"Elise","https://vid.tasty.co/output/20739/mp4_640x640/1484338324"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,117,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.rec2,"Potato Noodle",5,117,"Medium",45,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.egg_dish, "Sandwich with boiled egg", 5,200,"Easy",30,"Cheese, Tomato, egg,...",R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.buckwheat,"Buckwheat Granola with Maple Syrup", 5,117,"Easy",15,"Oat, Milk, Strawberry, Banana,..." ,R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.cauliflower_cheese,"Cauliflower cheese",3,10,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            )
        westernList.value = list
    }

    var vietnamList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadVietNamList() {
        val list = mutableListOf(
            Recipe(R.drawable.iv_banhmi, "Beef banh mi with fresh cilantro", 5,118,"Easy",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.iv_noodle, "Tomato and egg noodle soup", 5,1000,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.iv_braisedlargeshrimp, "Braised large shrimp", 5,500,"Medium",45,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.iv_c, "Vietnamese inspired noodle soup", 4,117,"Hard",120,"Enjoy each morning the delicious taste of Dinner",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            )
        vietnamList.value = list
    }

    var dessertList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadDessertList() {
        val list = mutableListOf(
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",5,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,400,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.jam_donut, "Jam doughnuts", 5, 300,"Medium", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
            Recipe(R.drawable.ivclassicapplestrudel, "Classic apple strudel", 4, 117,"Hard", 60, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
            Recipe(R.drawable.ivdumplings, "Sweet plum dumplings", 4, 210,"Medium", 30, "Deliciously fluffy and full of sticky, sweet strawberry jam, these homemade doughnuts will be snapped up by the family in no time. Learn how to make these sugary treats in just six easy steps, and don't forget to screenshot the ingredients list at the bottom for your next shopping trip.",R.drawable.profile2,"Elise","https://vid.tasty.co/output/32268/mp4_640x640/1495125535"),
            Recipe(R.drawable.ivsuper,"Superfood green smoothie",5,117,"Easy",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.iv_workout,"Post-workout smoothie", 4,10,"Easy",15,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Thanh Lam","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            //Recipe(R.drawable.iv_blue_smoothie, "Pomegranate blueberry smoothie", 3,11,"Easy",10,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.jam_tarts,"Jam Tarts",5,200,"Easy",30,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.strawberryjam, "Strawberry Jam", 4,110,"Medium",25,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),

        )
        dessertList.value = list
    }

    var drinkList = mutableListOf(
        Recipe(R.drawable.ivsuper,"Superfood green smoothie",5,117,"Easy",20,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile4,"Anna","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_workout,"Post-workout smoothie", 4,10,"Easy",15,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Thanh Lam","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
        Recipe(R.drawable.iv_blue_smoothie, "Pomegranate blueberry smoothie", 3,11,"Easy",10,"Enjoy each morning the delicious taste of breakfast",R.drawable.profile3,"Elise","https://vid.tasty.co/output/94177/landscape_480/1526421103"),
    )

    var saveList = MutableLiveData<MutableList<Recipe>>().also {
        it.value = mutableListOf()
    }
    fun loadSaveList() {
        val list = mutableListOf(
            Recipe(R.drawable.pancake,"Fluffy Blueberry Pancakes",4,117,"Medium",45,"Cheese, Honey, Blueberry, ...",R.drawable.profile4,"Anna" ,"https://vid.tasty.co/output/94177/landscape_480/1526421103"),
            Recipe(R.drawable.salted,"Salted Caramel Granola", 5,117,"Easy",15,"Enjoy each morning the delicious taste of breakfast" ,R.drawable.profile3,"Maria","https://vid.tasty.co/output/94177/landscape_480/1526421103"),

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