package com.example.ceibaapp.util

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.models.User
import com.example.ceibaapp.framework.data_source.cache.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class UserEntityToUserResponseModel @Inject constructor(private val userDao: UserDao) {

    private fun getUserResponseModelFromUserEntity(users: List<User>): List<UserResponseModel>{
        val userResponseModelList = ArrayList<UserResponseModel>()
        users.forEach {
            userResponseModelList.add(UserResponseModel(it.email, it.userId.toInt(), it.name, it.phone))
        }
         return userResponseModelList
    }

     fun getUsersFromDB(): List<UserResponseModel>{
         var userResponseModel :List<UserResponseModel> = ArrayList()
         runBlocking(Dispatchers.IO) {
             val job = async { getUserResponseModelFromUserEntity(userDao.getAllUsers()) }
             runBlocking {
                 userResponseModel = job.await()
             }
         }
            return  userResponseModel
     }

}