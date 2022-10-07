package com.app.authentication.database.repository

import com.app.authentication.database.entity.UserEntity

interface Repository {
    suspend fun insert(data: UserEntity): Boolean
    suspend fun check(email: String, key: String): UserEntity?
}