package com.example.ceibaapp.ui.userListFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ceibaapp.network.UserApi
import com.example.ceibaapp.network.responseModel.InitResponse
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.persistence.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserListFragmentRepository @Inject constructor(application: Application,
                                                     userDao: UserDao, retrofit: Retrofit) {
    private val TAG = "UserListRepository"
    //vars
    val application = application
    val userDao = userDao
    val retrofit = retrofit
    val userResponseModel: MutableLiveData<List<UserResponseModel>> = MutableLiveData()

    init {
        Log.i(TAG,"the injection over repository is working")
    }

    suspend fun getUserList() {
        val call = retrofit.create(UserApi::class.java).getUsers()
        withContext(Dispatchers.IO) {
            withTimeout(5000L) {
                call.enqueue(object : Callback<List<UserResponseModel>> {

                    override fun onResponse(call: Call<List<UserResponseModel>>,
                                            response: Response<List<UserResponseModel>>) {
                        if (response.code() == 200) {
                            response?.let {
                                userResponseModel.value = response.body()
                            }

                        }
                    }

                    override fun onFailure(call: Call<List<UserResponseModel>>, t: Throwable) {
                        Log.e(TAG,"An error was happen: ${t.message}")
                    }
                })
            }
        }
    }
}

