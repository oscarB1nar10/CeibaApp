package com.example.ceibaapp.business.data.network.implementation

import com.example.ceibaapp.business.data.network.abstraction.UserNetworkDataSource
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.network.implementation.UserNetworkServiceImpl
import javax.inject.Inject

class UserNetworkDataSourceImpl @Inject
constructor(
    private val userNetworkImpl: UserNetworkServiceImpl
) : UserNetworkDataSource {

    override suspend fun getUsers(): State<List<UserResponseModel>> {
        return userNetworkImpl.getUsers()
    }
}