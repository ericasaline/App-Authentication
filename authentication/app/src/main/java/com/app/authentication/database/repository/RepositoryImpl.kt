package com.app.authentication.database.repository

import com.app.authentication.database.dao.UserRoom
import com.app.authentication.database.entity.UserEntity

class RepositoryImpl(private val room: UserRoom): Repository {
    override suspend fun insert(data: UserEntity): Boolean {
        return try {
            room.insert(data)
            true
        } catch(e: Exception) {
            false
        }
    }

    override suspend fun check(email: String, key: String): UserEntity? {
        return try {
            room.check(email, key)
        } catch(e: Exception) {
            null
        }
    }
}