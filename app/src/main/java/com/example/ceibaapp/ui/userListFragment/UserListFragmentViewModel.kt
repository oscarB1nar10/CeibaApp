package com.example.ceibaapp.ui.userListFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListFragmentViewModel @Inject
        constructor(private var userListFragmentRepository: UserListFragmentRepository) : ViewModel(){

    private val TAG = "UserListViewModel"
    val userResponseModel = userListFragmentRepository.userResponseModel

    init {
        Log.i(TAG,"the injection over ViewModel is working")
    }

    fun getUsers(){
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                userListFragmentRepository.getUsers()
            }
            job.join()
        }
    }
}