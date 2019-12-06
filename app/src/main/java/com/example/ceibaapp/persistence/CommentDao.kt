package com.example.ceibaapp.persistence

import androidx.room.*
import com.example.ceibaapp.models.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<Comment>)

    @Transaction
    @Query("SELECT * FROM Comment WHERE userCreatorId = (:userId)")
    fun getCommentsFromSpecificUser(userId: Long): List<Comment>
}