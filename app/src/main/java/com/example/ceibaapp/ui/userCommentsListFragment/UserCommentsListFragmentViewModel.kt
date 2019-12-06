package com.example.ceibaapp.ui.userCommentsListFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceibaapp.models.User
import com.example.ceibaapp.network.responseModel.UserResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserCommentsListFragmentViewModel @Inject
constructor(userCommentsListFragmentRepository: UserCommentsListFragmentRepository) : ViewModel() {

    private val TAG = "UserListViewModel"
    private var userCommentsListFragmentRepository = userCommentsListFragmentRepository
    val userCommentResponseModel = userCommentsListFragmentRepository.userCommentResponseModel

    init {
        Log.i(TAG, "the injection over ViewModel is working")
    }

    fun getUserCommentList(user: UserResponseModel) {
        viewModelScope.launch(Dispatchers.Unconfined) {
            val job = GlobalScope.launch(Dispatchers.Unconfined) {
                userCommentsListFragmentRepository.getUserCommentList(user)
            }
            job.join()
        }
    }
}