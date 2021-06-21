package com.example.ceibaapp.framework.presentation.userCommentsListFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.business.interactors.GetComments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCommentsListFragmentViewModel @Inject
constructor(private val getComments: GetComments) :
    ViewModel() {

    private val TAG = "UserListViewModel"
    private var _userComments = MutableLiveData<State<List<UserCommentResponseModel>>>()
    val usersComments: LiveData<State<List<UserCommentResponseModel>>>
        get() = _userComments

    init {
        Log.i(TAG, "the injection over ViewModel is working")
    }

    fun getComments(user: UserResponseModel) {
        _userComments.value = State.loading()
        viewModelScope.launch(Dispatchers.Default) {
            _userComments.postValue(getComments.getComments(user))
        }
    }
}