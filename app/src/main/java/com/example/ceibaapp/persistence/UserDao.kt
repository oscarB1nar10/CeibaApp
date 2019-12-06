package com.example.ceibaapp.persistence

import androidx.room.*
import com.example.ceibaapp.models.Comment
import com.example.ceibaapp.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE userId = (:userId)")
    fun findUserById(userId: IntArray): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Transaction
    @Query("SELECT * FROM Comment WHERE userCreatorId = (:userId)")
    fun getCommentsFromSpecificUser(userId: Long): List<Comment>
}
