package com.example.foodlife.models

import android.net.Uri
import com.example.foodlife.R
import java.io.Serializable

data class AddRecipe(
        var name: String?,
        var description: String,
        var imgUri: Uri? = null,
        var vidUri: Uri? = null,
        var serves: Int,
        var prepTime: Int,
        var cookTime: Int,
        var difficulty: String,
        var ingredient: MutableList<DetailIngredients>,
        var directions: MutableList<DetailDirections>,
        var color : Int = R.color.secondary_100
): Serializable
