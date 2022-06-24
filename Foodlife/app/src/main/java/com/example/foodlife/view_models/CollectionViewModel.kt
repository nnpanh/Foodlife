package com.example.foodlife.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is collection Fragment"
    }
    val text: LiveData<String> = _text
}