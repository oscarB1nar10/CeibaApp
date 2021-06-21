package com.example.ceibaapp.business.data.cache.abstraction

import com.example.ceibaapp.business.domain.models.Comment

interface CommentCacheDataSource {
    fun insertComments(comments: List<Comment>)
    fun getCommentsFromSpecificUser(userId: Long): List<Comment>
}