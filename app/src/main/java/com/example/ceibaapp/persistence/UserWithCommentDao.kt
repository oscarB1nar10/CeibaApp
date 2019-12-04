package com.example.ceibaapp.persistence

import androidx.room.Query
import androidx.room.Transaction
import com.example.ceibaapp.models.UserWithComment

interface UserWithCommentDao {
    @Transaction
    @Query("SELECT * FROM Comment WHERE userCreatorId = (:userId)")
    fun getCommentsFromSpecificUser(userId: Long): List<UserWithComment>
}