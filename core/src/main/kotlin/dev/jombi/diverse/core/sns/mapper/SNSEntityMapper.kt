package dev.jombi.diverse.core.sns.mapper

import dev.jombi.diverse.business.sns.dto.SNSDto
import dev.jombi.diverse.core.member.domain.entity.Member
import dev.jombi.diverse.core.sns.domain.entity.MemberSNS
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface SNSEntityMapper {
    @Component
    companion object {
        @Bean
        fun snsEntityMapper(): SNSEntityMapper = Mappers.getMapper(SNSEntityMapper::class.java)
    }

    @Mapping(target = ".", source = "dto")
    fun mapToEntity(dto: SNSDto, member: Member): MemberSNS

    fun mapToDto(dto: MemberSNS): SNSDto
}
