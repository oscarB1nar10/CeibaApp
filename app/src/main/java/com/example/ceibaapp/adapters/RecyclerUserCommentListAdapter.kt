package com.example.ceibaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ceibaapp.R
import com.example.ceibaapp.network.responseModel.UserCommentResponseModel

class RecyclerUserCommentListAdapter : RecyclerView.Adapter<RecyclerUserCommentListAdapter.ViewHolder>() {
    //vars
    var comments: List<UserCommentResponseModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvTitle.text = comments[position].title
        holder.txvBody.text = comments[position].body
    }



    fun submitMovieList(comments: List<UserCommentResponseModel>){
        val oldList = this.comments
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            MovieItemDiffCallBack(
                oldList,
                comments
            )
        )

        this.comments = comments
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieItemDiffCallBack(
        var oldMovieDetail: List<UserCommentResponseModel>,
        var newMovieDetail: List<UserCommentResponseModel>
    ): DiffUtil.Callback(){
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
        val txvTitle: TextView = itemView.findViewById(R.id.title)
        val txvBody: TextView = itemView.findViewById(R.id.body)
    }

}