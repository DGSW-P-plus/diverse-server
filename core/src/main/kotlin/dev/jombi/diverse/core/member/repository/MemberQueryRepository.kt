package dev.jombi.diverse.core.member.repository

import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.core.gender.domain.entity.Gender
import dev.jombi.diverse.core.member.domain.entity.Member
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface MemberQueryRepository {
    fun findAllWithSortingAndPagination(pageable: Pageable, genders: List<Gender>): Slice<MemberDto>
    fun findOneWithMember(user: Member): MemberDto
}
