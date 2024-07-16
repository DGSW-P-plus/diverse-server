package dev.jombi.diverse.core.member.entity

import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import kotlin.jvm.optionals.getOrNull

@JvmInline
value class MemberId(val id: Long) {
    fun fetch(repository: MemberJpaRepository) = repository.findByIdOrNull(id)
}
