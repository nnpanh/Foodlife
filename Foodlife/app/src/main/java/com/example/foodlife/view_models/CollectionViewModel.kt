package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodlife.R
import com.example.foodlife.models.Recipe
import com.example.foodlife.models.Collection

class CollectionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is collection Fragment"
    }
    val text: LiveData<String> = _text
    var colList = MutableLiveData<MutableList<Collection>>().also {
        it.value = mutableListOf()
    }

    var result = false

    fun loadCollection() {
        val recipes = mutableListOf(
            Recipe(R.drawable.col1_rep1,"Roast beef with red wine & banana shallots ",5,"Easy",80,"",R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.col1_rep2,"Pot-roast beef with French onion gravy", 5,"Easy",135,"",R.drawable.profile3,"Thanh Lam",""),
            Recipe(R.drawable.col1_rep3,"Bacon & roast onion salad", 5,"Easy",30,"" ,R.drawable.profile4,"Anna",""),
        )
        val recipes1 = mutableListOf(
            Recipe(R.drawable.col2_rep1,"Scout's lobster rolls",5,"Medium",20,"",R.drawable.profile4,"Anna",""),
            Recipe(R.drawable.col2_rep2,"Pasta puttanesca", 4,"Easy",30,"",R.drawable.profile3,"Thanh Lam",""),
            Recipe(R.drawable.col2_rep3, "Korean fish pancakes", 5,"Difficult",25,"",R.drawable.profile3,"Elise",""),
        )
        val recipes2 = mutableListOf(
            Recipe(R.drawable.col3_rep1,"One-Pot Tomato Basil Pasta", 5,"Easy",60,"" ,R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.col3_rep2, "Veggie & Hummus Sandwich", 4,"",20,"",R.drawable.profile3,"Maria",""),
            Recipe(R.drawable.col3_rep3,"Baked Banana-Nut Oatmeal Cups", 4,"Easy",65,"",R.drawable.profile3,"Thanh Lam","" ),
        )

        val list = mutableListOf(
            Collection(R.drawable.img_collection,"", "Meat Lover", 3, recipes),
            Collection(R.drawable.col2,"", "Healthy", 3, recipes1),
            Collection(R.drawable.col1,"", "Diet", 3, recipes2)
        )
        colList.value = list
    }

    fun addCollection(newCol: Collection) {
        colList.value!!.add(newCol)
    }

    fun addRecipe(index: Int, recipe: Recipe) {
        colList.value!![index].quantity++
        colList.value!![index].recipes.add(recipe)
    }
}