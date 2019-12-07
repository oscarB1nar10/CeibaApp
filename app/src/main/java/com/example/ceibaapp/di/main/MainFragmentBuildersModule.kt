package com.example.ceibaapp.di.main

import com.example.ceibaapp.ui.userCommentsListFragment.UserCommentsListFragment
import com.example.ceibaapp.ui.userCommentsListFragment.UserCommentsListFragmentRepository
import com.example.ceibaapp.ui.userListFragment.UserFragment
import com.example.ceibaapp.ui.userListFragment.UserListFragmentRepository
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeUserCommentsListFragment(): UserCommentsListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserListFragmentRepository() : UserListFragmentRepository

    @ContributesAndroidInjector
    abstract fun contributeUserCommentsListFragmentRepository() : UserCommentsListFragmentRepository

}