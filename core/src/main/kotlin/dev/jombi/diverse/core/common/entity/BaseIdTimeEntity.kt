package dev.jombi.diverse.core.common.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseIdTimeEntity(
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private val _createdAt: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private val _updatedAt: LocalDateTime? = null,

    id: FetchableId = FetchableId.NULL
) : BaseIdEntity(id) {
    val createdAt get() = _createdAt
    val updatedAt get() = _updatedAt
}
