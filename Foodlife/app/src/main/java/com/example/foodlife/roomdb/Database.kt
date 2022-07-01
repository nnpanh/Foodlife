package com.example.foodlife.roomdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodlife.roomdb.entities.UserEntity


/**
 * Created by trilm on 7/16/18.
 */
@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)

abstract class FoodlifeDB : RoomDatabase() {

    abstract fun userDAO(): UserDao

    companion object {
        private val TAG = "Database"
        private val DATABASE_NAME = "foodlife"
        private var INSTANCE: FoodlifeDB? = null

        @JvmStatic
        fun getInstance(mContext: Context): FoodlifeDB {
            if (INSTANCE == null) {
                synchronized(FoodlifeDB::class) {
                    INSTANCE = buildDatabase(mContext)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(mContext: Context): FoodlifeDB {
            return Room.databaseBuilder(mContext, FoodlifeDB::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                            Log.e(TAG, "create database success")
                    }
                }).build()
        }

    }
}