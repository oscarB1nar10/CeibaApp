package com.example.ceibaapp.ui.userListFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListFragmentViewModel @Inject
        constructor(userListFragmentRepository: UserListFragmentRepository) : ViewModel(){

    private val TAG = "UserListViewModel"
    private var userListFragmentRepository = userListFragmentRepository
    val userResponseModel = userListFragmentRepository.userResponseModel

    init {
        Log.i(TAG,"the injection over ViewModel is working")
    }

    fun getUserList(){
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                userListFragmentRepository.getUserList()
            }
            job.join()
        }
    }
}