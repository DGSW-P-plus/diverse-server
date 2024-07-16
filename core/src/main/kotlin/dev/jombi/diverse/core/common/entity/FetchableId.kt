package dev.jombi.diverse.core.common.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

@JvmInline
value class FetchableId(private val _id: Long? = null) {
    val id get() = _id!!
    fun <T : Any> fetch(repository: JpaRepository<T, Long>) = repository.findByIdOrNull(id)

    companion object {
        val NULL = FetchableId(null)
    }
}
