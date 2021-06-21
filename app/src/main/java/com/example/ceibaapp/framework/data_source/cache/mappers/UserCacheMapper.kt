package com.example.ceibaapp.framework.data_source.cache.mappers

import com.example.ceibaapp.business.data.network.response_models.UserResponseModel
import com.example.ceibaapp.business.domain.models.User
import com.example.ceibaapp.business.domain.util.EntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCacheMapper
@Inject
constructor() : EntityMapper<User, UserResponseModel> {

    override fun mapFromEntityToDomain(entity: User): UserResponseModel {
        return UserResponseModel(
            entity.email,
            entity.userId.toInt(),
            entity.name,
            entity.phone
        )
    }

    override fun mapFromDomainToEntity(domainModel: UserResponseModel): User {
        return User(
            domainModel.id.toLong(),
            domainModel.name,
            domainModel.phone,
            domainModel.email
        )
    }

    fun mapFromDomainListToEntityList(userResponseList: List<UserResponseModel>): List<User> {
        val userListEntity = ArrayList<User>()
        userResponseList.forEach { userModel ->
            userListEntity.add(mapFromDomainToEntity(userModel))
        }
        return userListEntity
    }

    fun mapFromEntityListToDomainList(userList: List<User>): List<UserResponseModel> {
        val userListDomain = ArrayList<UserResponseModel>()
        userList.forEach { userList ->
            userListDomain.add(mapFromEntityToDomain(userList))
        }
        return userListDomain
    }

}