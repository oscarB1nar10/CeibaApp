package com.example.ceibaapp.framework.data_source.cache.abstraction

import com.example.ceibaapp.business.domain.models.Comment

interface CommentDaoService {
    fun insertComments(comments: List<Comment>)
    fun getCommentsFromSpecificUser(userId: Long): List<Comment>
}