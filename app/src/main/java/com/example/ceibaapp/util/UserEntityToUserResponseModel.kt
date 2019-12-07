package com.example.ceibaapp.util

import com.example.ceibaapp.models.User
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.persistence.UserDao
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