package dev.jombi.diverse.core.gender.repository

import dev.jombi.diverse.core.gender.domain.entity.MemberGender
import dev.jombi.diverse.core.gender.domain.entity.MemberGenderKey
import dev.jombi.diverse.core.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberGenderJpaRepository : JpaRepository<MemberGender, MemberGenderKey> {
    fun findAllByMemberIs(member: Member): List<MemberGender>
}
