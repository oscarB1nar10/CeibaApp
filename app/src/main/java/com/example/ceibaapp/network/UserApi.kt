package com.example.ceibaapp.network

import com.example.ceibaapp.network.responseModel.UserResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getUsers() : Call<List<UserResponseModel>>
}