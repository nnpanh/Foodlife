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
    fun loadCollection() {
        val list = mutableListOf(
            Collection("", "Meat Lover", 1),
            Collection("", "Healthy", 4),
            Collection("", "Diet", 4)
        )
        colList.value = list
    }
    fun addCollection(newCol: Collection) {
        colList.value!!.add(newCol)
    }
}