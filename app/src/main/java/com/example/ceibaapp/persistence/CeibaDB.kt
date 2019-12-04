package com.example.ceibaapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceibaapp.models.Comment
import com.example.ceibaapp.models.User
import com.example.ceibaapp.models.UserWithComment

@Database(
    entities = [User::class, Comment::class, UserWithComment::class],
    version = 1
)
abstract class CeibaDB : RoomDatabase() {

    companion object {
        //const
        const val DATABASENAME = "Ceiba_db"
    }

    abstract fun getUserDao(): UserDao
    abstract fun getCommentDao(): CommentDao
    abstract fun getUserWithCommentDao(): UserWithCommentDao

}