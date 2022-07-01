package com.example.foodlife.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class MealEntity(
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "image") var image: Int?,
    @ColumnInfo(name = "totalRecipe") var totalRecipe: Int?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}