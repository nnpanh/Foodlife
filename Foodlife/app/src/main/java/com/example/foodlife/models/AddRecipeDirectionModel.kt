package com.example.foodlife.models

import android.net.Uri

data class AddRecipeDirectionModel(
    var direcion: String,
    var num: Int,
    var imageURI: Uri?
    )