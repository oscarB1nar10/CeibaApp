package com.example.ceibaapp.persistence

import androidx.room.Insert
import com.example.ceibaapp.models.Comment

interface CommentDao {
    @Insert
    fun insertComments(vararg comments: Comment)
}