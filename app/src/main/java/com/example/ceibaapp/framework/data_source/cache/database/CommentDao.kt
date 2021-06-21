package com.example.ceibaapp.framework.data_source.cache.database

import androidx.room.*
import com.example.ceibaapp.business.domain.models.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(comments: List<Comment>)

    @Transaction
    @Query("SELECT * FROM Comment WHERE userCreatorId = (:userId)")
    fun getCommentsFromSpecificUser(userId: Long): List<Comment>
}