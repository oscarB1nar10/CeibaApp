package com.example.ceibaapp.ui.userCommentsListFragment


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
import com.example.ceibaapp.adapters.RecyclerUserCommentListAdapter
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.viewModelFactory.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_user_comments_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserCommentsListFragment : DaggerFragment() {
    //const
    private val TAG = "UserCommentListFrag"

    //vars
    private lateinit var pgMain: ProgressBar
    private lateinit var userResponseModel: UserResponseModel
    private lateinit var userCommentsListFragmentViewModel: UserCommentsListFragmentViewModel
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory
    @Inject
    lateinit var recyclerUserCommentListAdapter: RecyclerUserCommentListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userResponseModel = arguments!!.getParcelable("user")!!
        return inflater.inflate(R.layout.fragment_user_comments_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userCommentsListFragmentViewModel = ViewModelProvider(this,viewModelProvFactory).
            get(UserCommentsListFragmentViewModel::class.java)
        addUserInfo()
        initViewComponents()
        getComments()
    }

    private fun addUserInfo() {
        name.text = userResponseModel.name
        phone.text = userResponseModel.phone
        email.text = userResponseModel.email
    }


    private fun initViewComponents() {
        pgMain = activity!!.findViewById(R.id.pg_main)
        recyclerViewPostsResults.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerViewPostsResults.adapter = recyclerUserCommentListAdapter
    }

    private fun getComments() {
        pgMain.visibility = View.VISIBLE
        getCommentListObserve()
        userCommentsListFragmentViewModel.getComments(userResponseModel)
    }

    private fun getCommentListObserve(){
        userCommentsListFragmentViewModel.userCommentResponseModel.removeObservers(viewLifecycleOwner)
        userCommentsListFragmentViewModel.userCommentResponseModel.observe(viewLifecycleOwner, Observer {
            pgMain.visibility = View.GONE
            it?.let {
                recyclerUserCommentListAdapter.submitCommentList(it)
                Log.i(TAG, "users: $it")
            }
        })
    }




}
