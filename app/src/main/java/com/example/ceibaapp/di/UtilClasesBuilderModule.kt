package com.example.ceibaapp.di

import com.example.ceibaapp.util.NetworkState
import com.example.ceibaapp.util.UserCommentResponseModelToEntity
import com.example.ceibaapp.util.UserEntityToUserResponseModel
import com.example.ceibaapp.util.UserListResponseModelToEntity
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




}