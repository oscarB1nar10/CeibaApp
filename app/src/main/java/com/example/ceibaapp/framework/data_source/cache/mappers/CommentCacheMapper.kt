package com.example.ceibaapp.framework.data_source.cache.mappers

import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.domain.models.Comment
import com.example.ceibaapp.business.domain.util.EntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentCacheMapper
@Inject
constructor() : EntityMapper<Comment, UserCommentResponseModel> {

    override fun mapFromEntityToDomain(entity: Comment): UserCommentResponseModel {
        return UserCommentResponseModel(
            entity.description,
            entity.commentId.toInt(),
            entity.title,
            entity.userCreatorId.toInt()
        )
    }

    override fun mapFromDomainToEntity(domainModel: UserCommentResponseModel): Comment {
        return Comment(
            domainModel.id.toLong(),
            domainModel.userId.toLong(),
            domainModel.title,
            domainModel.body
        )
    }


    fun mapFromDomainListToEntityList(UserCommentResponseList: List<UserCommentResponseModel>): List<Comment> {
        val userCommentListEntity = ArrayList<Comment>()
        UserCommentResponseList.forEach { userCommentModel ->
            userCommentListEntity.add(mapFromDomainToEntity(userCommentModel))
        }
        return userCommentListEntity
    }

    fun mapFromEntityListToDomainList(userCommentList: List<Comment>): List<UserCommentResponseModel> {
        val userCommentListDomain = ArrayList<UserCommentResponseModel>()
        userCommentList.forEach { userCommentList ->
            userCommentListDomain.add(mapFromEntityToDomain(userCommentList))
        }
        return userCommentListDomain
    }

}