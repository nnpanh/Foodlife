package com.example.foodlife.models

import com.example.foodlife.R
import java.io.Serializable

data class Collection(
        var oldImg: Int?,
        var img: String,
        var title: String,
        var quantity: Int,
        var recipes: MutableList<Recipe>,
        var color : Int = R.color.secondary_100
): Serializable
