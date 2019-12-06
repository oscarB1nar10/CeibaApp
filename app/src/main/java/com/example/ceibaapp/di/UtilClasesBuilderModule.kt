package com.example.ceibaapp.di

import com.example.ceibaapp.util.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UtilClasesBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeNetworkState(): NetworkState

    @ContributesAndroidInjector
    abstract fun contributeUserListResponseModelToEntity(): UserListResponseModelToEntity

    @ContributesAndroidInjector
    abstract fun contributeUserEntityToUserResponseModel(): UserEntityToUserResponseModel

    @ContributesAndroidInjector
    abstract fun contributeUserCommentResponseModelToEntity(): UserCommentResponseModelToEntity

    @ContributesAndroidInjector
    abstract fun contributeCommentEntityToUserCommentResponseModel(): CommentEntityToUserCommentResponseModel

}