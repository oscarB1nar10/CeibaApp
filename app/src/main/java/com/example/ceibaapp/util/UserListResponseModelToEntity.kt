package com.example.ceibaapp.util

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.models.User
import com.example.ceibaapp.framework.data_source.cache.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListResponseModelToEntity @Inject constructor(private val userDao: UserDao) {

    fun getUserEntityFromUserListResponseModel(userResponseModelList: List<UserResponseModel>){
        val userList = ArrayList<User>()
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