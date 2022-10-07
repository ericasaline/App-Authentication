package com.app.authentication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.authentication.database.dao.UserRoom
import com.app.authentication.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun dao(): UserRoom
}