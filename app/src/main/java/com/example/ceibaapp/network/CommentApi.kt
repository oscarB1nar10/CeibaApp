package com.example.ceibaapp.network

import com.example.ceibaapp.models.Comment
import retrofit2.Call
import retrofit2.http.GET

interface CommentApi{

    @GET("posts")
    fun getComments() : Call<Comment>
}