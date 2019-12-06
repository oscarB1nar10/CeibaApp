package com.example.ceibaapp.util

import com.example.ceibaapp.models.Comment
import com.example.ceibaapp.network.responseModel.UserCommentResponseModel
import com.example.ceibaapp.persistence.CommentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserCommentResponseModelToEntity @Inject constructor(commentDao: CommentDao) {

    val commentDao = commentDao

    fun insertUserCommentEntityFromUserCommentResponseModel(userCommentResponseModel: List<UserCommentResponseModel>){
        var commentList = ArrayList<Comment>()
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