package dev.jombi.diverse.core.sns.repository

import dev.jombi.diverse.core.member.domain.entity.Member
import dev.jombi.diverse.core.sns.domain.consts.SNSType
import dev.jombi.diverse.core.sns.domain.entity.MemberSNS
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberSNSJpaRepository : JpaRepository<MemberSNS, Long> {
    fun getAllByMember(member: Member): List<MemberSNS>
    fun existsByMemberAndType(member: Member, type: SNSType): Boolean
}
