package com.example.ceibaapp.business.data.network.implementation

import com.example.ceibaapp.business.data.network.abstraction.CommentNetworkDataSource
import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.network.implementation.CommentNetworkServiceImpl
import javax.inject.Inject

class CommentNetworkDataSourceImpl @Inject
constructor(
    private val commentNetworkServiceImpl: CommentNetworkServiceImpl
) : CommentNetworkDataSource {

    override suspend fun getComments(user: UserResponseModel): State<List<UserCommentResponseModel>> {
        return commentNetworkServiceImpl.getComments(user)
    }
}