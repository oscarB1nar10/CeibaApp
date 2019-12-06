package com.example.ceibaapp.network

import com.example.ceibaapp.network.responseModel.UserCommentResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi{

    @GET("posts")
    fun getComments(
        @Query("userId") userId: Long) : Call<List<UserCommentResponseModel>>
}