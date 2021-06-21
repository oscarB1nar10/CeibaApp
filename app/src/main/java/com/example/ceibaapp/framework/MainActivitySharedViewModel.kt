package com.example.ceibaapp.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivitySharedViewModel : ViewModel(){

    private var _isShowProgressBar = MutableLiveData<Boolean>()
    val isShowProgressBar: LiveData<Boolean>
        get() = _isShowProgressBar


    fun setShowProgressBar(showProgressBar: Boolean = true) {
        _isShowProgressBar.value = showProgressBar
    }

}