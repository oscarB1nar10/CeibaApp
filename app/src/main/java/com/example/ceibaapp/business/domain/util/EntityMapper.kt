package com.example.ceibaapp.business.domain.util

interface EntityMapper<Entity, DomainModel>{

    fun mapFromEntityToDomain(entity: Entity): DomainModel

    fun mapFromDomainToEntity(domainModel: DomainModel): Entity
}