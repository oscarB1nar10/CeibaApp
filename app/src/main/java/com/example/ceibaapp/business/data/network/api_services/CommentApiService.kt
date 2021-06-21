package com.example.ceibaapp.business.data.network.api_services

import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentApiService{

    @GET("posts")
    suspend fun getComments(
        @Query("userId") userId: Long) : List<UserCommentResponseModel>
}