package com.example.ceibaapp.di

import android.app.Application
import androidx.room.Room
import com.example.ceibaapp.persistence.CeibaDB
import com.example.ceibaapp.persistence.CeibaDB.Companion.DATABASENAME
import com.example.ceibaapp.persistence.CommentDao
import com.example.ceibaapp.persistence.UserDao
import com.example.ceibaapp.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object{

        @JvmStatic
        @Singleton
        @Provides
        fun provideNoteDatabase(application: Application):CeibaDB{
            return Room.databaseBuilder(
                application,
                CeibaDB::class.java,
                DATABASENAME
            ).build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideNoteDao(ceibaDB: CeibaDB): UserDao{
            return ceibaDB.getUserDao()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideCommentDao(ceibaDB: CeibaDB): CommentDao{
            return ceibaDB.getCommentDao()
        }

        @Singleton
        @JvmStatic
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return  Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}