package com.example.ceibaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.example.ceibaapp.ui.userListFragment.UserFragment

class RecyclerUserListAdapter constructor(private val view: UserFragment) :
                    RecyclerView.Adapter<RecyclerUserListAdapter.ViewHolder>() {
    //vars
    private var users: List<UserResponseModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvName.text = users[position].name
        holder.txvContentPhone.text = users[position].phone
        holder.txvEmail.text = users[position].email
        holder.btnViewPost.setOnClickListener {
            val bundle = bundleOf("user" to users.get(position))
            view.findNavController().navigate(R.id.action_usersListFragment_to_userCommentsListFragment_dest, bundle)
        }
    }

    fun submitUserList(users: List<UserResponseModel>){
        val oldList = this.users
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            UserItemDiffCallBack(
                oldList,
                users
            )
        )

        this.users = users
        diffResult.dispatchUpdatesTo(this)
    }

    class UserItemDiffCallBack(
        private var oldUserDetail: List<UserResponseModel>,
        private var newUserDetail: List<UserResponseModel>
    ):DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldUserDetail[oldItemPosition].id == newUserDetail[newItemPosition].id)
        }

        override fun getOldListSize(): Int {
            return oldUserDetail.size
        }

        override fun getNewListSize(): Int {
            return newUserDetail.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldUserDetail[oldItemPosition] == newUserDetail[newItemPosition]
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txvName: TextView = itemView.findViewById(R.id.name)
        val txvContentPhone: TextView = itemView.findViewById(R.id.phone)
        val txvEmail: TextView = itemView.findViewById(R.id.email)
        val btnViewPost: Button = itemView.findViewById(R.id.btn_view_post)

    }

}