package com.example.ceibaapp.framework.data_source.cache.implementation

import com.example.ceibaapp.framework.data_source.cache.abstraction.UserDaoService
import com.example.ceibaapp.business.domain.models.User
import com.example.ceibaapp.framework.data_source.cache.database.UserDao
import javax.inject.Inject

class UserDaoServiceImpl
@Inject
constructor(
    private val userDao: UserDao
): UserDaoService{

    override suspend fun getAllUsers(): List<User> {
        //TODO("Map cache entity to business model")
        return userDao.getAllUsers()
    }

    override suspend fun findUserById(userId: Int): User? {
        //TODO("Map cache entity to business model")
        return userDao.findUserById(userId)
    }

    override suspend fun insertUsers(users: List<User>) {
        //TODO("Map cache entity to business model")
        return userDao.insertUsers(users)
    }

}