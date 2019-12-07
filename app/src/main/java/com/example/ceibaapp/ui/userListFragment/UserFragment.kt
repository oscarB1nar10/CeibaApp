package com.example.ceibaapp.ui.userListFragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.adapters.RecyclerUserListAdapter
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.viewModelFactory.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject

/**
 * Created by B1nar10 (Oscar Ivan Ramirez) - 06/12/2019
 * A simple [Fragment] subclass to list users.
 * All rights reserved
 */
class UserFragment : DaggerFragment() {

    private val TAG = "UsersListFragment"
    //vars
    private var userList: List<UserResponseModel> = ArrayList()
    private lateinit var pgMain: ProgressBar
    private lateinit var recyclerUserListAdapter: RecyclerUserListAdapter
    private lateinit var userListFragmentViewModel: UserListFragmentViewModel
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userListFragmentViewModel = ViewModelProvider(this,viewModelProvFactory).
            get(UserListFragmentViewModel::class.java)
        initViewComponents()
        getUsers()
    }

    private fun initViewComponents() {
        pgMain = activity!!.findViewById(R.id.pg_main)
        editTextSearch.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(textFilter: Editable?) {
                filterUsers(textFilter.toString())
            }
            //region badFunc
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            //endregion badFunc
        })
        recyclerViewSearchResults.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerUserListAdapter = RecyclerUserListAdapter(this)
        recyclerViewSearchResults.adapter = recyclerUserListAdapter
    }

    private fun filterUsers(textFilter: String){
        recyclerUserListAdapter.let {
            val userResponseModelList = ArrayList<UserResponseModel>()
            userList.forEach {
                if(it.name.contains(textFilter)){
                    userResponseModelList.add(it)
                }
            }
            recyclerUserListAdapter.submitUserList(userResponseModelList)
            recyclerUserListAdapter.notifyDataSetChanged()
        }
    }

    private fun getUsers() {
        pgMain.visibility = View.VISIBLE
        editTextSearch.text.clear()
        getUserListObserve()
        userListFragmentViewModel.getUsers()
    }

    private fun getUserListObserve(){
        userListFragmentViewModel.userResponseModel.removeObservers(viewLifecycleOwner)
        userListFragmentViewModel.userResponseModel.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            it?.let {
                recyclerUserListAdapter.submitUserList(it)
                userList = it
                Log.i(TAG, "users: $it")
            }
        })
    }

}
