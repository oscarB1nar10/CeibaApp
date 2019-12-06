package com.example.ceibaapp.ui.userListFragment


import android.os.Bundle
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
import com.example.ceibaapp.viewModelFactory.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UsersListFragment : DaggerFragment() {

    private val TAG = "UsersListFragment"
    //vars
    lateinit var pgMain: ProgressBar
    lateinit var recyclerUserListAdapter: RecyclerUserListAdapter
    lateinit var userListFragmentViewModel: UserListFragmentViewModel
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userListFragmentViewModel = ViewModelProvider(this,viewModelProvFactory).
                                    get(UserListFragmentViewModel::class.java)
        initViewComponents()
        getUserList()
    }

    private fun initViewComponents() {
        pgMain = activity!!.findViewById(R.id.pg_main)
        recyclerViewSearchResults.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerUserListAdapter = RecyclerUserListAdapter(this)
        recyclerViewSearchResults.adapter = recyclerUserListAdapter
    }

    private fun getUserList() {
        pgMain.visibility = View.VISIBLE
        getUserListObserve()
        userListFragmentViewModel.getUserList()
    }

    private fun getUserListObserve(){
        userListFragmentViewModel.userResponseModel.removeObservers(viewLifecycleOwner)
        userListFragmentViewModel.userResponseModel.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            it?.let {
                recyclerUserListAdapter.submitMovieList(it)
                Log.i(TAG, "users: $it")
            }
        })
    }


}
