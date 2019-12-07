package com.example.ceibaapp.util

import com.example.ceibaapp.models.Comment
import com.example.ceibaapp.network.responseModel.UserCommentResponseModel
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.persistence.CommentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CommentEntityToUserCommentResponseModel @Inject constructor(private val commentDao: CommentDao) {

    private  fun getUserCommentResponseModelFromUserEntity(comments: List<Comment>): List<UserCommentResponseModel> {
        val userCommentResponseModelList = ArrayList<UserCommentResponseModel>()
        comments.forEach {
            userCommentResponseModelList.add(
                UserCommentResponseModel(
                    it.description,
                    it.commentId.toInt(),
                    it.title,
                    it.userCreatorId.toInt()
                )
            )
        }
        return userCommentResponseModelList
    }

    fun getUsersCommentsFromDB(user: UserResponseModel): List<UserCommentResponseModel> {
        var userCommentResponseModelList: List<UserCommentResponseModel> = ArrayList()
        runBlocking(Dispatchers.IO) {
            val job = async {
                getUserCommentResponseModelFromUserEntity(
                    commentDao.getCommentsFromSpecificUser(user.id.toLong())
                )
            }
            runBlocking {
                userCommentResponseModelList = job.await()
            }
        }
        return userCommentResponseModelList
    }
}