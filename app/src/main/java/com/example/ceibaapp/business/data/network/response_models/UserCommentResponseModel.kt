package com.example.ceibaapp.network.responseModel

data class UserCommentResponseModel(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
){
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }

        other as UserCommentResponseModel

        if(id != other.id){
            return false
        }
        if(userId != other.userId){
            return false
        }
        if(title != other.title){
            return false
        }
        if(body != other.body){
            return false
        }
        return true
    }
}