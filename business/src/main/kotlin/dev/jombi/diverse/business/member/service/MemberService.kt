package dev.jombi.diverse.business.member.service

import dev.jombi.diverse.business.member.dto.MemberDto

interface MemberService {
    fun me(): MemberDto
}
