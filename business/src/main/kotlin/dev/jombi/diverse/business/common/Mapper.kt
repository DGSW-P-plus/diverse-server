package dev.jombi.diverse.business.common

interface Mapper<D, E> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}