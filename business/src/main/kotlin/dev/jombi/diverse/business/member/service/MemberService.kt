package dev.jombi.diverse.business.member.service

import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.member.dto.MemberOptionalDto

interface MemberService {
    fun me(): MemberDto
    fun editMyInfo(info: MemberOptionalDto)
}
