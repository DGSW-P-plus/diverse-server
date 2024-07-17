package dev.jombi.diverse.core.sns.mapper

import dev.jombi.diverse.business.sns.dto.SNSDto
import dev.jombi.diverse.core.common.entity.BaseIdEntity
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

//    @Mapping(target = ".", expression = "java(dev.jombi.diverse.core.sns.domain.consts.SNSType.valueOf(dto.type))")
    fun mapToEntity(dto: SNSDto, member: Member): MemberSNS

//    @Mapping(target = "type", expression = "java(dto.type.name)")
    fun mapToDto(dto: MemberSNS): SNSDto
}
