package com.example.foodlife.models

import android.net.Uri
import java.io.Serializable

class DetailDirections(
    var img: Int,
    var regular: String,
    var bold: String,
    var isSelected: Boolean = false,
    var imgUri: Uri? = null
) : Serializable