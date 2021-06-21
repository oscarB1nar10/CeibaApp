package com.example.ceibaapp.framework.data_source.network.implementation

import com.example.ceibaapp.business.data.network.api_services.UserApiService
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.cache.abstraction.UserDaoService
import com.example.ceibaapp.framework.data_source.cache.mappers.UserCacheMapper
import com.example.ceibaapp.framework.data_source.network.abstraction.UserNetworkService
import javax.inject.Inject

class UserNetworkServiceImpl @Inject
constructor(
    private val userApiService: UserApiService,
    private val userDaoService: UserDaoService,
    private val userCacheMapper: UserCacheMapper
) : UserNetworkService {

    override suspend fun getUsers(): State<List<UserResponseModel>> {
        val users = userApiService.getUsers()
        if (users.isNotEmpty()) {
            val userEntityList = userCacheMapper.mapFromDomainListToEntityList(users)
            userDaoService.insertUsers(userEntityList)
            return State.success(
                userCacheMapper.mapFromEntityListToDomainList(userDaoService.getAllUsers()))
        }
        return State.success(listOf())
    }

}