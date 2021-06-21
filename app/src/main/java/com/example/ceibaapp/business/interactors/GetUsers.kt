package com.example.ceibaapp.business.interactors

import com.example.ceibaapp.business.data.network.abstraction.UserNetworkDataSource
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.cache.abstraction.UserDaoService
import com.example.ceibaapp.framework.data_source.cache.mappers.UserCacheMapper
import com.example.ceibaapp.util.*
import javax.inject.Inject

class GetUsers @Inject
constructor(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val userDaoService: UserDaoService,
    private val userCacheMapper: UserCacheMapper,
    private val networkState: NetworkState
) {

    suspend fun getUsers(): State<List<UserResponseModel>> {
        return if (networkState.withInternetConnection()) {
            if (userDaoService.getAllUsers().isEmpty()) {
                userNetworkDataSource.getUsers()
            } else {
                State.success(
                    userCacheMapper.mapFromEntityListToDomainList(userDaoService.getAllUsers())
                )
            }
        } else {
            State.success(userCacheMapper.mapFromEntityListToDomainList(userDaoService.getAllUsers()))
        }
    }
}