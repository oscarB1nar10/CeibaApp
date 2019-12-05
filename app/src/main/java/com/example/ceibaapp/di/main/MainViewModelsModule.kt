package com.example.ceibaapp.di.main

import androidx.lifecycle.ViewModel
import com.example.ceibaapp.di.ViewModelKey
import com.example.ceibaapp.ui.userListFragment.UserListFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule{


    @Binds
    @IntoMap
    @ViewModelKey(UserListFragmentViewModel::class)
    abstract fun bindUserListFragmentViewModel(viewModel: UserListFragmentViewModel): ViewModel

}