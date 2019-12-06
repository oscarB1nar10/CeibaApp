package com.example.ceibaapp.di.main

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.ceibaapp.adapters.RecyclerUserCommentListAdapter
import com.example.ceibaapp.adapters.RecyclerUserListAdapter
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