package com.example.ceibaapp.di

import android.app.Application
import androidx.room.Room
import com.example.ceibaapp.business.data.cache.abstraction.CommentCacheDataSource
import com.example.ceibaapp.business.data.cache.abstraction.UserCacheDataSource
import com.example.ceibaapp.business.data.cache.implementation.CommentCacheDataSourceImpl
import com.example.ceibaapp.business.data.cache.implementation.UserCacheDataSourceImpl
import com.example.ceibaapp.framework.data_source.cache.abstraction.CommentDaoService
import com.example.ceibaapp.framework.data_source.cache.abstraction.UserDaoService
import com.example.ceibaapp.framework.data_source.cache.database.CeibaDB
import com.example.ceibaapp.framework.data_source.cache.database.CommentDao
import com.example.ceibaapp.framework.data_source.cache.database.UserDao
import com.example.ceibaapp.framework.data_source.cache.implementation.CommentDaoServiceImpl
import com.example.ceibaapp.framework.data_source.cache.implementation.UserDaoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideNoteDatabase(application: Application): CeibaDB {
        return Room.databaseBuilder(
            application,
            CeibaDB::class.java,
            CeibaDB.DATABASENAME
        ).build()
    }

    @Provides
    fun provideNoteDao(ceibaDB: CeibaDB): UserDao {
        return ceibaDB.getUserDao()
    }

    @Provides
    fun provideCommentDao(ceibaDB: CeibaDB): CommentDao {
        return ceibaDB.getCommentDao()
    }

    @Provides
    fun provideUserDaoService(userDao: UserDao): UserDaoService {
        return UserDaoServiceImpl(userDao)
    }

    @Provides
    fun provideCommentDaoService(commentDao: CommentDao): CommentDaoService {
        return CommentDaoServiceImpl(commentDao)
    }

    @Provides
    fun provideUserCacheDataSource(userDaoServiceImpl: UserDaoServiceImpl): UserCacheDataSource {
        return UserCacheDataSourceImpl(userDaoServiceImpl)
    }

    @Provides
    fun provideCommentCacheDataSource(commentDaoServiceImpl: CommentDaoServiceImpl): CommentCacheDataSource {
        return CommentCacheDataSourceImpl(commentDaoServiceImpl)
    }

}