package com.example.ceibaapp.ui.userCommentsListFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ceibaapp.network.CommentApi
import com.example.ceibaapp.network.UserApi
import com.example.ceibaapp.network.responseModel.UserCommentResponseModel
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.util.CommentEntityToUserCommentResponseModel
import com.example.ceibaapp.util.NetworkState
import com.example.ceibaapp.util.UserCommentResponseModelToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserCommentsListFragmentRepository @Inject constructor(//vars
    val application: Application,
    private val retrofit: Retrofit,
    private val networkState: NetworkState,
    val userCommentResponseModelToEntity: UserCommentResponseModelToEntity,
    private val commentEntityToUserCommentResponseModel: CommentEntityToUserCommentResponseModel
){

    private val TAG = "UserCommListRepository"
    var userCommentResponseModel: MutableLiveData<List<UserCommentResponseModel>> = MutableLiveData()

    init {
        Log.i(TAG,"the injection over repository is working")
    }

    suspend fun getComments(user: UserResponseModel) {
        val call = retrofit.create(CommentApi::class.java).getComments(user.id.toLong())
        if(networkState.getNetworkState()) {
            if (commentEntityToUserCommentResponseModel.getUsersCommentsFromDB(user).isEmpty()) {
            withContext(Dispatchers.IO) {
                withTimeout(5000L) {
                    call.enqueue(object : Callback<List<UserCommentResponseModel>> {

                        override fun onResponse(
                            call: Call<List<UserCommentResponseModel>>,
                            response: Response<List<UserCommentResponseModel>>
                        ) {
                            if (response.code() == 200) {
                                response.let {
                                    userCommentResponseModel.value = response.body()
                                    userCommentResponseModelToEntity.insertUserCommentEntityFromUserCommentResponseModel(
                                        response.body()!!
                                    )
                                }

                            }
                        }

                        override fun onFailure(
                            call: Call<List<UserCommentResponseModel>>,
                            t: Throwable
                        ) {
                            Log.e(TAG, "An error was happen: ${t.message}")

                        }
                    })
                }
            }
        }else{
                userCommentResponseModel.value = commentEntityToUserCommentResponseModel.getUsersCommentsFromDB(user)
        }
        }else{
            Log.i(TAG,"Without internet: " +
                    "${commentEntityToUserCommentResponseModel.getUsersCommentsFromDB(user)}")
            userCommentResponseModel.value = commentEntityToUserCommentResponseModel.getUsersCommentsFromDB(user)

        }
    }
}

