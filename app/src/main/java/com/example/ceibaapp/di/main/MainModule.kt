package com.example.ceibaapp.di.main

import com.example.ceibaapp.adapters.RecyclerUserListAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule{

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRecyclerUserListAdapter(): RecyclerUserListAdapter {
            return RecyclerUserListAdapter()}

    }

}