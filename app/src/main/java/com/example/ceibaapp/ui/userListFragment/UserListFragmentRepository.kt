package com.example.ceibaapp.ui.userListFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ceibaapp.network.UserApi
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.util.NetworkState
import com.example.ceibaapp.util.UserEntityToUserResponseModel
import com.example.ceibaapp.util.UserListResponseModelToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserListFragmentRepository @Inject constructor(//vars
    val application: Application,
    private val retrofit: Retrofit,
    private val networkState: NetworkState,
    val userListResponseModelToEntity: UserListResponseModelToEntity,
    private val userEntityToUserResponseModel: UserEntityToUserResponseModel
) {
    private val TAG = "UserListRepository"
    var userResponseModel: MutableLiveData<List<UserResponseModel>> = MutableLiveData()

    init {
        Log.i(TAG,"the injection over repository is working")
    }

    suspend fun getUsers() {
        val call = retrofit.create(UserApi::class.java).getUsers()
        if(networkState.getNetworkState()) {
            if(userEntityToUserResponseModel.getUsersFromDB().isEmpty()) {
                withContext(Dispatchers.IO) {
                    withTimeout(5000L) {
                        call.enqueue(object : Callback<List<UserResponseModel>> {

                            override fun onResponse(
                                call: Call<List<UserResponseModel>>,
                                response: Response<List<UserResponseModel>>
                            ) {
                                if (response.code() == 200) {
                                    response.let {
                                        userResponseModel.value = response.body()
                                        userListResponseModelToEntity.getUserEntityFromUserListResponseModel(
                                            response.body()!!
                                        )
                                    }

                                }
                            }

                            override fun onFailure(
                                call: Call<List<UserResponseModel>>,
                                t: Throwable
                            ) {
                                Log.e(TAG, "An error was happen: ${t.message}")
                            }
                        })
                    }
                }
            }else{
                userResponseModel.value = userEntityToUserResponseModel.getUsersFromDB()
            }
        }else{
            Log.i(TAG,"Without internet: " +
                    "${userEntityToUserResponseModel.getUsersFromDB()}")
            userResponseModel.value = userEntityToUserResponseModel.getUsersFromDB()

        }
    }
}

