package com.example.ceibaapp.framework.data_source.network.abstraction

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State

interface UserNetworkService {
    suspend fun getUsers(): State<List<UserResponseModel>>
}