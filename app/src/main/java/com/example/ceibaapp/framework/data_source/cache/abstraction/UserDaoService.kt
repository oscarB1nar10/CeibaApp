package com.example.ceibaapp.framework.data_source.cache.abstraction

import com.example.ceibaapp.business.domain.models.User

interface UserDaoService {
    suspend fun getAllUsers(): List<User>
    suspend fun findUserById(userId: Int): User?
    suspend fun insertUsers(users: List<User>)
}