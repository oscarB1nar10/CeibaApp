package com.example.ceibaapp.business.data.network.response_models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserResponseModel(
    val email: String,
    val id: Int,
    val name: String,
    val phone: String
): Parcelable{
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass){
            return false
        }

        other as UserResponseModel

        if(id != other.id){
            return false
        }
        if(name != other.name){
            return false
        }
        if(phone != other.phone){
            return false
        }
        if(email != other.email){
            return false
        }
        return true
    }
}



