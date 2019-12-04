package com.example.ceibaapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ceibaapp.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE userId = (:userId)")
    fun findUserById(userId: IntArray): List<User>

    @Insert
    fun insertUsers(vararg users: User)
}
