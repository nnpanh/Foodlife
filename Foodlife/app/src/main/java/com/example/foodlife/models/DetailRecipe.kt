package com.example.foodlife.models

import android.provider.MediaStore

data class DetailRecipe (
    var video: MediaStore.Video,
    var title: String,
    var score: Float,
    var prepare: Int,
    var cooking: Int,
    var diff: String,
    var description: String,
)