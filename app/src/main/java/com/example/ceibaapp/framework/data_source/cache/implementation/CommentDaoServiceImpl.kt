package com.example.ceibaapp.framework.data_source.cache.implementation

import com.example.ceibaapp.framework.data_source.cache.abstraction.CommentDaoService
import com.example.ceibaapp.business.domain.models.Comment
import com.example.ceibaapp.framework.data_source.cache.database.CommentDao
import javax.inject.Inject

class CommentDaoServiceImpl
@Inject
constructor(
    private val commentDao: CommentDao
    ): CommentDaoService {

    override fun insertComments(comments: List<Comment>) {
        //TODO("Map cache entity to business model")
        commentDao.insertComments(comments)
    }

    override fun getCommentsFromSpecificUser(userId: Long): List<Comment> {
        //TODO("Map cache entity to business model")
       return commentDao.getCommentsFromSpecificUser(userId)
    }

}