package dev.jombi.diverse.core.member.service

import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.member.dto.MemberOptionalDto
import dev.jombi.diverse.business.member.service.MemberService
import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberHolder: MemberHolder,
    private val memberJpaRepository: MemberJpaRepository
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

    @Transactional(rollbackFor = [Exception::class])
    override fun editMyInfo(info: MemberOptionalDto) {
        val holding = memberHolder.get()
        holding.nickname = info.nickname ?: holding.nickname
        holding.location = info.location ?: holding.location
        holding.bio = info.bio ?: holding.bio

        memberJpaRepository.save(holding)
    }
}
