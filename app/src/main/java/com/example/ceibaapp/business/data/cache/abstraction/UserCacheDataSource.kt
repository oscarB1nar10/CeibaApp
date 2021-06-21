package com.example.ceibaapp.business.data.cache.abstraction

import com.example.ceibaapp.business.domain.models.User

interface UserCacheDataSource {
    suspend fun getAllUsers(): List<User>
    suspend fun findUserById(userId: Int): User?
    suspend fun insertUsers(users: List<User>)
}