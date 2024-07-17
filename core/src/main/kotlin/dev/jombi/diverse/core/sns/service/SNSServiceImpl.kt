package dev.jombi.diverse.core.sns.service

import dev.jombi.diverse.business.sns.dto.SNSDto
import dev.jombi.diverse.business.sns.service.SNSService
import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.core.sns.mapper.SNSEntityMapper
import dev.jombi.diverse.core.sns.repository.MemberSNSJpaRepository
import org.springframework.stereotype.Service

@Service
class SNSServiceImpl(
    private val snsJpaRepository: MemberSNSJpaRepository,
    private val memberHolder: MemberHolder,
    private val snsEntityMapper: SNSEntityMapper
) : SNSService {
    override fun getMySNSList(): List<SNSDto> {
        val me = memberHolder.get()
        val ent = snsJpaRepository.getAllByMember(me)
        return ent.map(snsEntityMapper::mapToDto)
    }

    override fun addSNS(sns: SNSDto) {
        val me = memberHolder.get()

//        snsJpaRepository.existsByMemberAndType(sns.type)
    }

    override fun removeSNS(sns: SNSDto) {

    }
}
