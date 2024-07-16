package dev.jombi.diverse.core.member.repository

import dev.jombi.diverse.core.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long> {
    fun existsByCredential(credential: String): Boolean
    fun findMemberByCredential(credential: String): Member? // uniq
}
