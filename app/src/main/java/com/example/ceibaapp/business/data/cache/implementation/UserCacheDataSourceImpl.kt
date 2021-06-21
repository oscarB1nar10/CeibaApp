package com.example.ceibaapp.business.data.cache.implementation

import com.example.ceibaapp.business.data.cache.abstraction.UserCacheDataSource
import com.example.ceibaapp.framework.data_source.cache.implementation.UserDaoServiceImpl
import com.example.ceibaapp.business.domain.models.User
import javax.inject.Inject

class UserCacheDataSourceImpl
@Inject
constructor(
    private val userDaoServiceImpl: UserDaoServiceImpl
) : UserCacheDataSource {

    override suspend fun getAllUsers(): List<User> {
        return userDaoServiceImpl.getAllUsers()
    }

    override suspend fun findUserById(userId: Int): User? {
        return userDaoServiceImpl.findUserById(userId)
    }

    override suspend fun insertUsers(users: List<User>) {
        return userDaoServiceImpl.insertUsers(users)
    }
}