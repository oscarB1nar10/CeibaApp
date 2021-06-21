package com.example.ceibaapp.business.data.network.api_services

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUsers() : List<UserResponseModel>
}