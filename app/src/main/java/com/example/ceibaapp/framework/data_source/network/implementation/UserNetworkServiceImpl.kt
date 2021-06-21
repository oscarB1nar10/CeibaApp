package com.example.ceibaapp.framework.data_source.network.implementation

import android.app.Application
import com.example.ceibaapp.framework.data_source.network.abstraction.UserNetworkService
import com.example.ceibaapp.network.UserApiService
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.util.NetworkState
import com.example.ceibaapp.util.UserEntityToUserResponseModel
import com.example.ceibaapp.util.UserListResponseModelToEntity
import javax.inject.Inject

class UserNetworkImpl @Inject
constructor(
    val application: Application,
    private val userApiService: UserApiService,
    private val networkState: NetworkState,
    val userListResponseModelToEntity: UserListResponseModelToEntity,
    private val userEntityToUserResponseModel: UserEntityToUserResponseModel
) : UserNetworkService {

    override suspend fun getUsers(): List<UserResponseModel> {
        val users = userApiService.getUsers()
        if (users.isNotEmpty()) {
            userListResponseModelToEntity.getUserEntityFromUserListResponseModel(users)
            return userEntityToUserResponseModel.getUsersFromDB()
        }
        return listOf()
    }

}