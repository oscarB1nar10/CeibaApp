package com.example.ceibaapp.util

import androidx.lifecycle.MutableLiveData
import com.example.ceibaapp.models.User
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.persistence.UserDao
import kotlinx.coroutines.*
import javax.inject.Inject

class UserEntityToUserResponseModel @Inject constructor(userDao: UserDao) {

    val userDao = userDao

       private suspend fun getUserResponseModelFromUserEntity(users: List<User>): List<UserResponseModel>{
        var userResponseModelList = ArrayList<UserResponseModel>()
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