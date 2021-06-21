package com.example.ceibaapp.business.data.network.abstraction

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State


interface UserNetworkDataSource {
   suspend fun getUsers(): State<List<UserResponseModel>>
}