package com.example.ceibaapp.framework.data_source.network.implementation

import com.example.ceibaapp.business.data.network.api_services.CommentApiService
import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.data_source.cache.abstraction.CommentDaoService
import com.example.ceibaapp.framework.data_source.cache.mappers.CommentCacheMapper
import com.example.ceibaapp.framework.data_source.network.abstraction.CommentNetworkService
import javax.inject.Inject

class CommentNetworkServiceImpl @Inject
constructor(
    private val commentApiService: CommentApiService,
    private val commentDaoService: CommentDaoService,
    private val commentCacheMapper: CommentCacheMapper,
) : CommentNetworkService {

    override suspend fun getComments(user: UserResponseModel): State<List<UserCommentResponseModel>> {
        val userComments = commentApiService.getComments(user.id.toLong())
        return if (userComments.isNotEmpty()) {
            commentDaoService.insertComments(
                commentCacheMapper.mapFromDomainListToEntityList(
                    userComments
                )
            )
            State.success(commentCacheMapper.mapFromEntityListToDomainList(
                commentDaoService.getCommentsFromSpecificUser(user.id.toLong())
            ))
        } else {
            State.success(listOf())
        }
    }
}