package com.example.ceibaapp.framework.presentation.userListFragment


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.states.State
import com.example.ceibaapp.framework.presentation.adapters.RecyclerUserListAdapter
import com.example.ceibaapp.databinding.FragmentUsersListBinding
import com.example.ceibaapp.framework.MainActivitySharedViewModel
import com.example.library.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by B1nar10 (Oscar Ivan Ramirez) - 06/20/2021
 * A simple [Fragment] subclass to list users.
 * All rights reserved
 */
@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_users_list) {

    private val TAG = "UsersListFragment"

    //vars
    private var userList: List<UserResponseModel> = ArrayList()
    private lateinit var recyclerUserListAdapter: RecyclerUserListAdapter

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val userListFragmentViewModel: UserListFragmentViewModel by viewModels()
    // Using the activityViewModels() Kotlin property delegate from the
    // fragment-ktx artifact to retrieve the ViewModel in the activity scope
    private val mainActivitySharedViewModel: MainActivitySharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureUi()
        subscribeObservers()
    }

    private fun configureUi() {
        binding.editTextSearch.doAfterTextChanged { textFilter ->
            filterUsers(textFilter.toString())
        }

        recyclerUserListAdapter = RecyclerUserListAdapter(this@UserFragment)

        binding.recyclerViewSearchResults.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = recyclerUserListAdapter
        }
    }

    private fun filterUsers(textFilter: String) {
        recyclerUserListAdapter.let {
            val userResponseModelList = ArrayList<UserResponseModel>()
            userList.forEach {
                if (it.name.contains(textFilter)) {
                    userResponseModelList.add(it)
                }
            }
            recyclerUserListAdapter.submitUserList(userResponseModelList)
            recyclerUserListAdapter.notifyDataSetChanged()
        }
    }

    private fun subscribeObservers() = with(binding) {
        userListFragmentViewModel.getUsers()
        userListFragmentViewModel.users.observe(viewLifecycleOwner, ::onUsersResponse)
        editTextSearch.text.clear()
    }

    private fun onUsersResponse(state: State<List<UserResponseModel>>){
        when(state){
            is State.Loading -> {mainActivitySharedViewModel.setShowProgressBar()}
            is State.Success -> {
                mainActivitySharedViewModel.setShowProgressBar(false)

                recyclerUserListAdapter.submitUserList(state.data)
                userList = state.data
                Log.i(TAG, "users: $state")
            }
            is State.Failed -> {mainActivitySharedViewModel.setShowProgressBar(false)}
        }

    }

}
