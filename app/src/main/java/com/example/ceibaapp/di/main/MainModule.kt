package com.example.ceibaapp.di.main

import com.example.ceibaapp.adapters.RecyclerUserCommentListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule{

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRecyclerUserCommentListAdapter(): RecyclerUserCommentListAdapter {
            return RecyclerUserCommentListAdapter()}
    }

}