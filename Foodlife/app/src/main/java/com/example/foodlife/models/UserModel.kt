package com.example.foodlife.models

import com.example.foodlife.roomdb.entities.UserEntity

class UserModel(
    var id: Int?,
    var name: String?,
    private var avatar: Int?,
    private var totalRecipe: Int?,
    private var username: String?,
    private var password: String?
)
{
    private var hehe = false
    constructor() : this(0, "", 0, 0, "", "")
    constructor(name: String?, avatar: Int?, totalRecipe: Int?, username: String?, password: String?) :
            this(null, name, avatar, totalRecipe, username, password)

    fun convertToEntity(): UserEntity {
        return UserEntity(
            name,
            avatar,
            totalRecipe,
            username,
            password
        )
    }
}