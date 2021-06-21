package com.example.ceibaapp.framework.presentation.userCommentsListFragment


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.adapters.RecyclerUserCommentListAdapter
import com.example.ceibaapp.business.data.network.response_models.UserCommentResponseModel
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.databinding.FragmentUserCommentsListBinding
import com.example.ceibaapp.framework.MainActivitySharedViewModel
import com.example.library.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by B1nar10 (Oscar Ivan Ramirez) - 06/20/2021
 * A simple [Fragment] subclass to list users.
 * All rights reserved
 */
@AndroidEntryPoint
class UserCommentsListFragment : Fragment(R.layout.fragment_user_comments_list) {
    //const
    private val TAG = "UserCommentListFrag"

    //vars
    private lateinit var userResponseModel: UserResponseModel
    private lateinit var recyclerUserCommentListAdapter: RecyclerUserCommentListAdapter

    private val binding by viewBinding(FragmentUserCommentsListBinding::bind)
    private val userListFragmentViewModel: UserCommentsListFragmentViewModel by viewModels()

    // Using the activityViewModels() Kotlin property delegate from the
    // fragment-ktx artifact to retrieve the ViewModel in the activity scope
    private val mainActivitySharedViewModel: MainActivitySharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        userResponseModel = arguments?.getParcelable("user")!!

        addUserInfo()
        configureUi()
        subscribeObservers()
    }

    private fun addUserInfo() = with(binding) {
        name.text = userResponseModel.name
        phone.text = userResponseModel.phone
        email.text = userResponseModel.email
    }


    private fun configureUi() = with(binding) {
        recyclerUserCommentListAdapter = RecyclerUserCommentListAdapter()
        recyclerViewPostsResults.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = recyclerUserCommentListAdapter
        }
    }

    private fun subscribeObservers() {
        userListFragmentViewModel.getComments(userResponseModel)
        userListFragmentViewModel.usersComments.observe(
            viewLifecycleOwner,
            ::onUserCommentsResponse
        )
    }

    private fun onUserCommentsResponse(state: State<List<UserCommentResponseModel>>) {
        when (state) {
            is State.Loading -> {
                mainActivitySharedViewModel.setShowProgressBar()
            }
            is State.Success -> {
                mainActivitySharedViewModel.setShowProgressBar(false)

                recyclerUserCommentListAdapter.submitCommentList(state.data)
                Log.i(TAG, "users: $state.data")
            }
            is State.Failed -> {
                mainActivitySharedViewModel.setShowProgressBar(false)
            }
        }

    }

}
