package com.example.ceibaapp.network

import com.example.ceibaapp.network.responseModel.UserResponseModel
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUsers() : List<UserResponseModel>
}