package com.example.ceibaapp.business.data.network.abstraction

import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State

interface CommentNetworkDataSource {
    suspend fun getComments(user: UserResponseModel): State<List<UserCommentResponseModel>>
}