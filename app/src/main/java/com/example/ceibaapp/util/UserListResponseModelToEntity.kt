package com.example.ceibaapp.util

import androidx.lifecycle.MutableLiveData
import com.example.ceibaapp.models.User
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.persistence.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListResponseModelToEntity @Inject constructor(userDao: UserDao) {

    val userDao = userDao

    fun getUserEntityFromUserListResponseModel(userResponseModelList: List<UserResponseModel>){
        var userList = ArrayList<User>()
        userResponseModelList.forEach {
            userList.add(User(it.id.toLong(), it.name, it.phone, it.email))
        }
        insertUsersInDB(userList)
    }

    private fun insertUsersInDB(users: List<User>){
        GlobalScope.launch(Dispatchers.Unconfined) {
            val job =  launch(Dispatchers.IO) {
                userDao.insertUsers(users)
            }
            job.join()
        }
    }
}