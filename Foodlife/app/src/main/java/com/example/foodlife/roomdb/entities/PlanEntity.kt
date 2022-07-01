package com.example.foodlife.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
data class PlanEntity(
    @ColumnInfo(name = "userId") var useId: Int?,
    @ColumnInfo(name = "time") var time: String?, //Time = 'Breakfast','Lunch','Dinner','Snack'
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "mealId") var mealId: Int?, //Id of the meal
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}