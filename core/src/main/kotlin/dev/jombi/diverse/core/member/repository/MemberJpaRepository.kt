package dev.jombi.diverse.core.member.repository

import dev.jombi.diverse.core.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Member?
}
