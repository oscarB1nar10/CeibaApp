package com.example.ceibaapp.framework.data_source.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ceibaapp.business.domain.models.Comment
import com.example.ceibaapp.business.domain.models.User

@Database(
    entities = [User::class, Comment::class,],
    version = 1,
    exportSchema = false
)
abstract class CeibaDB : RoomDatabase() {

    companion object {
        //const
        const val DATABASENAME = "Ceiba_db"
    }

    abstract fun getUserDao(): UserDao
    abstract fun getCommentDao(): CommentDao

}