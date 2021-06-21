package com.example.ceibaapp.network

import com.example.ceibaapp.network.responseModel.UserCommentResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApiService{

    @GET("posts")
    suspend fun getComments(
        @Query("userId") userId: Long) : List<UserCommentResponseModel>
}