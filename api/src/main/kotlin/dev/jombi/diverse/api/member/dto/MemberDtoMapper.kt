package dev.jombi.diverse.api.member.dto

import dev.jombi.diverse.api.member.dto.response.MemberInfoResponse
import dev.jombi.diverse.business.member.dto.MemberDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface MemberDtoMapper {
    @Component
    companion object {
        @Bean
        fun memberInstance(): MemberDtoMapper = Mappers.getMapper(MemberDtoMapper::class.java)
    }

    fun convertToResponse(request: MemberDto): MemberInfoResponse
}
