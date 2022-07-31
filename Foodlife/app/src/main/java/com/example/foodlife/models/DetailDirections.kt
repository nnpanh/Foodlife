package com.example.foodlife.models

import java.io.Serializable

class DetailDirections(
    var img: Int,
    var regular: String,
    var bold: String,
    var isSelected: Boolean = false
) : Serializable