package com.example.ceibaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.network.responseModel.UserResponseModel
import com.squareup.picasso.Picasso

class RecyclerUserListAdapter : RecyclerView.Adapter<RecyclerUserListAdapter.ViewHolder>() {
    //vars
    var users: List<UserResponseModel> = ArrayList()

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
    }



    fun submitMovieList(users: List<UserResponseModel>){
        val oldList = this.users
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallBack(
                oldList,
                users
            )
        )

        this.users = users
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallBack(
        var oldMovieDetail: List<UserResponseModel>,
        var newMovieDetail: List<UserResponseModel>
    ):DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldMovieDetail.get(oldItemPosition).id == newMovieDetail.get(newItemPosition).id)
        }

        override fun getOldListSize(): Int {
            return oldMovieDetail.size
        }

        override fun getNewListSize(): Int {
            return newMovieDetail.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldMovieDetail[oldItemPosition] == newMovieDetail[newItemPosition]
        }

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txvName: TextView = itemView.findViewById(R.id.name)
        val txvContentPhone: TextView = itemView.findViewById(R.id.phone)
        val txvEmail: TextView = itemView.findViewById(R.id.email)

    }

}