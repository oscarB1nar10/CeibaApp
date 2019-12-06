package com.example.ceibaapp.ui.userCommentsListFragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.adapters.RecyclerUserCommentListAdapter
import com.example.ceibaapp.ui.userListFragment.UserListFragmentViewModel
import com.example.ceibaapp.viewModelFactory.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserCommentsListFragment : DaggerFragment() {

    //vars
    lateinit var userCommentsListFragmentViewModel: UserCommentsListFragmentViewModel
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory
    @Inject
    lateinit var recyclerUserCommentListAdapter: RecyclerUserCommentListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_comments_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userCommentsListFragmentViewModel = ViewModelProvider(this,viewModelProvFactory).
            get(UserCommentsListFragmentViewModel::class.java)
        initViewComponents()
        getUserList()
    }

    private fun initViewComponents() {
        recyclerViewSearchResults.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerViewSearchResults.adapter = recyclerUserListAdapter
    }

    private fun getUserList() {
        pg_main.visibility = View.VISIBLE
        getUserListObserve()
        userListFragmentViewModel.getUserList()
    }

    private fun getUserListObserve(){
        userListFragmentViewModel.userResponseModel.removeObservers(viewLifecycleOwner)
        userListFragmentViewModel.userResponseModel.observe(viewLifecycleOwner, Observer {
            pg_main.visibility = View.GONE
            it?.let {
                recyclerUserListAdapter.submitMovieList(it)
                Log.i(TAG, "users: $it")
            }
        })
    }


}
