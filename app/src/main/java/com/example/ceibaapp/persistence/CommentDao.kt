package com.example.ceibaapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import com.example.ceibaapp.models.Comment

@Dao
interface CommentDao {
    @Insert
    fun insertComments(comments: List<Comment>)
}