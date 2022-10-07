package com.app.authentication.database.dao

import androidx.room.*
import com.app.authentication.database.entity.UserEntity

@Dao
interface UserRoom {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email = :email AND `key` = :key")
    suspend fun check(email: String, key: String): UserEntity
}