package dev.jombi.diverse.core.member.service

import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.member.service.MemberService
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberHolder: MemberHolder
) : MemberService {
    override fun me(): MemberDto {
        val member = memberHolder.get()
        return MemberDto(
            credential = member.credential,
            name = member.name,
            location = member.location,
            bio = member.bio
        )
    }
}
