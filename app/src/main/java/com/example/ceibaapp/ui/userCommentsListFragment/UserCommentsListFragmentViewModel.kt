package com.example.ceibaapp.ui.userCommentsListFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserCommentsListFragmentViewModel @Inject
constructor(userCommentsListFragmentRepository: UserCommentsListFragmentRepository) : ViewModel() {

    private val TAG = "UserListViewModel"
    private var userListFragmentRepository = userCommentsListFragmentRepository
    val userResponseModel = userListFragmentRepository.userCommentResponseModel

    init {
        Log.i(TAG, "the injection over ViewModel is working")
    }

    fun getUserCommentList() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                userListFragmentRepository.getUserCommentList()
            }
            job.join()
        }
    }
}