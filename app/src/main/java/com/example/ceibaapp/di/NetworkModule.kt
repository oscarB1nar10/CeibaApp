package com.example.ceibaapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.ceibaapp.business.data.network.abstraction.CommentNetworkDataSource
import com.example.ceibaapp.business.data.network.abstraction.UserNetworkDataSource
import com.example.ceibaapp.business.data.network.api_services.CommentApiService
import com.example.ceibaapp.business.data.network.api_services.UserApiService
import com.example.ceibaapp.business.data.network.implementation.CommentNetworkDataSourceImpl
import com.example.ceibaapp.business.data.network.implementation.UserNetworkDataSourceImpl
import com.example.ceibaapp.business.interactors.GetComments
import com.example.ceibaapp.business.interactors.GetUsers
import com.example.ceibaapp.framework.data_source.cache.abstraction.CommentDaoService
import com.example.ceibaapp.framework.data_source.cache.abstraction.UserDaoService
import com.example.ceibaapp.framework.data_source.cache.mappers.CommentCacheMapper
import com.example.ceibaapp.framework.data_source.cache.mappers.UserCacheMapper
import com.example.ceibaapp.framework.data_source.network.implementation.CommentNetworkServiceImpl
import com.example.ceibaapp.framework.data_source.network.implementation.UserNetworkServiceImpl
import com.example.ceibaapp.util.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    fun provideCommentApiService(retrofit: Retrofit): CommentApiService {
        return retrofit.create(CommentApiService::class.java)
    }

    @Provides
    fun provideUserNetworkServiceImpl(
        userApiService: UserApiService,
        userDaoService: UserDaoService,
        userCacheMapper: UserCacheMapper
    ): UserNetworkServiceImpl {
        return UserNetworkServiceImpl(
            userApiService,
            userDaoService,
            userCacheMapper
        )
    }

    @Provides
    fun provideCommentNetworkServiceImpl(
        commentApiService: CommentApiService,
        commentDaoService: CommentDaoService,
        commentCacheMapper: CommentCacheMapper
    ): CommentNetworkServiceImpl {
        return CommentNetworkServiceImpl(
            commentApiService,
            commentDaoService,
            commentCacheMapper
        )
    }

    @Provides
    fun provideUserNetworkDataSource(userNetworkImpl: UserNetworkServiceImpl): UserNetworkDataSource {
        return UserNetworkDataSourceImpl(userNetworkImpl)
    }

    @Provides
    fun provideCommentNetworkDataSource(commentNetworkServiceImpl: CommentNetworkServiceImpl): CommentNetworkDataSource {
        return CommentNetworkDataSourceImpl(commentNetworkServiceImpl)
    }

    @Provides
    fun provideGetUsers(
        userNetworkDataSource: UserNetworkDataSource,
        networkState: NetworkState,
        userDaoService: UserDaoService,
        userCacheMapper: UserCacheMapper,
    ): GetUsers {
        return GetUsers(
            userNetworkDataSource,
            userDaoService,
            userCacheMapper,
            networkState
        )
    }

    @Provides
    fun provideGetComments(
        commentNetworkDataSource: CommentNetworkDataSource,
        networkState: NetworkState,
        commentDaoService: CommentDaoService,
        commentCacheMapper: CommentCacheMapper,
    ): GetComments {
        return GetComments(
            commentNetworkDataSource,
            commentDaoService,
            commentCacheMapper,
            networkState
        )
    }
}