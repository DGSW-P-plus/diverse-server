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
            id = member.id.id,
            username = member.username,
            nickname = member.nickname,
            location = member.location,
            bio = member.bio
        )
    }
}
