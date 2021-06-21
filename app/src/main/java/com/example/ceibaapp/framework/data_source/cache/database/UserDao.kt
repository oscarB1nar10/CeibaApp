package com.example.ceibaapp.framework.data_source.cache.database

import androidx.room.*
import com.example.ceibaapp.business.domain.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE userId = (:userId)")
    fun findUserById(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

}
