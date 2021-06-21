package com.example.ceibaapp.business.interactors

import com.example.ceibaapp.business.data.network.abstraction.CommentNetworkDataSource
import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.cache.abstraction.CommentDaoService
import com.example.ceibaapp.framework.data_source.cache.mappers.CommentCacheMapper
import com.example.ceibaapp.util.NetworkState
import javax.inject.Inject


class GetComments @Inject
constructor(
    private val commentNetworkDataSource: CommentNetworkDataSource,
    private val commentDaoService: CommentDaoService,
    private val commentCacheMapper: CommentCacheMapper,
    private val networkState: NetworkState,
) {

    suspend fun getComments(user: UserResponseModel): State<List<UserCommentResponseModel>> {
        return if (networkState.withInternetConnection()) {
            if (commentDaoService.getCommentsFromSpecificUser(user.id.toLong()).isEmpty()) {
                commentNetworkDataSource.getComments(user)
            } else {
                State.success(
                    commentCacheMapper.mapFromEntityListToDomainList(
                        commentDaoService.getCommentsFromSpecificUser(user.id.toLong())
                    )
                )
            }
        } else {
            State.Success(
                commentCacheMapper.mapFromEntityListToDomainList(
                    commentDaoService.getCommentsFromSpecificUser(user.id.toLong())
                )
            )
        }
    }
}