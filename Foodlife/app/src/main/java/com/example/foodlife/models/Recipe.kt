package com.example.foodlife.models

import java.io.Serializable

data class Recipe(
    var img: Int,
    var title: String,
    var score: Int,
    var diff: String,
    var time: Int,
    var description: String,
    var profile_img:Int,
    var profile_name:String,
    var video_url:String,
): Serializable