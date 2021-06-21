package com.example.ceibaapp.framework.presentation.userListFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.business.interactors.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListFragmentViewModel @Inject
constructor(private val getUsers: GetUsers) : ViewModel() {

    private val TAG = "UserListViewModel"
    private var _users = MutableLiveData<State<List<UserResponseModel>>>()
    val users: LiveData<State<List<UserResponseModel>>>
        get() = _users

    init {
        Log.i(TAG, "the injection over ViewModel is working")
    }

    fun getUsers() {
        _users.value = State.Loading()
        viewModelScope.launch(Dispatchers.Default) {
            _users.postValue(getUsers.getUsers())
        }
    }
}