package com.trivago.core.base

interface BaseMapper {

    interface ToDomain<ENTITY, DOMAIN> {
        fun mapToDomain(entity: ENTITY): DOMAIN
    }

    interface ToEntity<ENTITY, DOMAIN> {
        fun mapToEntity(domain: DOMAIN): ENTITY
    }
}
