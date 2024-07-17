package dev.jombi.diverse.core.member.service

import dev.jombi.diverse.business.member.dto.HasNextMemberDto
import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.member.dto.MemberOptionalDto
import dev.jombi.diverse.business.member.service.MemberService
import dev.jombi.diverse.core.gender.repository.MemberGenderQueryRepository
import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import dev.jombi.diverse.core.member.repository.MemberQueryRepository
import dev.jombi.diverse.core.sns.mapper.SNSEntityMapper
import dev.jombi.diverse.core.sns.repository.MemberSNSJpaRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
    private val memberHolder: MemberHolder,
    private val memberJpaRepository: MemberJpaRepository,
    private val memberQueryRepository: MemberQueryRepository,
    private val genderQueryRepository: MemberGenderQueryRepository,
    private val memberSNSJpaRepository: MemberSNSJpaRepository,
    private val snsEntityMapper: SNSEntityMapper,
) : MemberService {
    override fun me(): MemberDto {
        val member = memberHolder.get()
        val genders = genderQueryRepository.findGenderByUserId(member.id.id)
        val sns = memberSNSJpaRepository.getAllByMember(member)
        return MemberDto(
            id = member.id.id,
            username = member.username,
            nickname = member.nickname,
            location = member.location,
            bio = member.bio,
            genders.map { it.mapDto() },
            sns.map { snsEntityMapper.mapToDto(it) }
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

    override fun findWithPagination(info: Pageable): HasNextMemberDto {
        val member = memberHolder.get()
        val members = memberQueryRepository.justPaginationIgnoreSelf(info, member)

        val slice = members.map {
            val genders = genderQueryRepository.findGenderByUserId(it.id.id)
            val sns = memberSNSJpaRepository.getAllByMember(it)

            MemberDto(
                it.id.id,
                it.username,
                it.nickname,
                it.location,
                it.bio,
                genders.map { it.mapDto() },
                sns.map(snsEntityMapper::mapToDto)
            )
        }

        return HasNextMemberDto(slice.hasNext(), slice.toList())
    }
}
