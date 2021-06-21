package com.example.ceibaapp.util

import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.domain.models.Comment
import com.example.ceibaapp.framework.data_source.cache.database.CommentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserCommentResponseModelToEntity @Inject constructor(private val commentDao: CommentDao) {

    fun insertUserCommentEntityFromUserCommentResponseModel(userCommentResponseModel: List<UserCommentResponseModel>){
        val commentList = ArrayList<Comment>()
        userCommentResponseModel.forEach {
            commentList.add(Comment(it.id.toLong(),it.userId.toLong(), it.title, it.body))
        }
        insertUsersInDB(commentList)
    }

    private fun insertUsersInDB(comments: List<Comment>){
        GlobalScope.launch(Dispatchers.Unconfined) {
            val job =  launch(Dispatchers.IO) {
                commentDao.insertComments(comments)
            }
            job.join()
        }
    }
}