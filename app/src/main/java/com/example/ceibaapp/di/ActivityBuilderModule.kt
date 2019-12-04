package com.example.ceibaapp.di

import com.example.ceibaapp.MainActivity
import com.example.ceibaapp.di.main.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector
    abstract fun contributeMaintActivity(): MainActivity
}