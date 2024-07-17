package dev.jombi.diverse.business.member.service

import dev.jombi.diverse.business.member.dto.HasNextMemberDto
import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.member.dto.MemberOptionalDto
import org.springframework.data.domain.Pageable

interface MemberService {
    fun me(): MemberDto
    fun editMyInfo(info: MemberOptionalDto)
    fun findWithPagination(info: Pageable): HasNextMemberDto
}
