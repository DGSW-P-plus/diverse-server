package dev.jombi.diverse.core.gender.service

import dev.jombi.diverse.business.gender.dto.GenderDto
import dev.jombi.diverse.business.gender.service.GenderService
import dev.jombi.diverse.core.gender.domain.entity.MemberGender
import dev.jombi.diverse.core.gender.repository.GenderJpaRepository
import dev.jombi.diverse.core.gender.repository.MemberGenderJpaRepository
import dev.jombi.diverse.core.gender.repository.MemberGenderQueryRepository
import dev.jombi.diverse.core.member.MemberHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GenderServiceImpl(
    private val genderJpaRepository: GenderJpaRepository,
    private val memberGenderJpaRepository: MemberGenderJpaRepository,
    private val memberGenderQueryRepository: MemberGenderQueryRepository,
    private val holder: MemberHolder
) : GenderService {
    override fun getGenders(): List<GenderDto> {
        return genderJpaRepository.findAll().map { it.mapDto() }
    }

    override fun myGenders(): List<GenderDto> {
        return memberGenderQueryRepository.findGenderByUserId(holder.get().id.id).map { it.mapDto() }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun setMemberGenders(set: List<Long>) {
        val member = holder.get()
        val holding = memberGenderJpaRepository.findAllByMemberIs(member)
        memberGenderJpaRepository.deleteAll(holding)

        val gender = genderJpaRepository.findAllById(set)
            .map { MemberGender(it, member) }

        memberGenderJpaRepository.saveAll(gender)
    }
}
