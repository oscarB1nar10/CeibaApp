package com.example.ceibaapp.business.data.cache.implementation

import com.example.ceibaapp.business.data.cache.abstraction.CommentCacheDataSource
import com.example.ceibaapp.framework.data_source.cache.implementation.CommentDaoServiceImpl
import com.example.ceibaapp.business.domain.models.Comment
import javax.inject.Inject

class CommentCacheDataSourceImpl @Inject
constructor(
    private val commentDaoServiceImpl: CommentDaoServiceImpl
    ): CommentCacheDataSource{

    override fun insertComments(comments: List<Comment>) {
        commentDaoServiceImpl.insertComments(comments)
    }

    override fun getCommentsFromSpecificUser(userId: Long): List<Comment> {
        return commentDaoServiceImpl.getCommentsFromSpecificUser(userId)
    }

}