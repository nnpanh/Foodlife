package com.example.foodlife.models

import java.io.Serializable

data class Collection(
        var oldImg: Int?,
        var img: String,
        var title: String,
        var quantity: Int,
): Serializable