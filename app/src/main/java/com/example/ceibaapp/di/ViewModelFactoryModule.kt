package com.example.ceibaapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.ceibaapp.viewModelFactory.ViewModelProvFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvFactory: ViewModelProvFactory): ViewModelProvider.Factory

}