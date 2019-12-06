package com.example.ceibaapp.di.main

import com.example.ceibaapp.adapters.RecyclerUserListAdapter
import com.example.ceibaapp.ui.userCommentsListFragment.UserCommentsListFragment
import com.example.ceibaapp.ui.userCommentsListFragment.UserCommentsListFragmentRepository
import com.example.ceibaapp.ui.userListFragment.UsersListFragment
import com.example.ceibaapp.ui.userListFragment.UserListFragmentRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule{

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UsersListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserCommentsListFragment(): UserCommentsListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserListFragmentRepository() : UserListFragmentRepository

    @ContributesAndroidInjector
    abstract fun contributeUserCommentsListFragmentRepository() : UserCommentsListFragmentRepository

}