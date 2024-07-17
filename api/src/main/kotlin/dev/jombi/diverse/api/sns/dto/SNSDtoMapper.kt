package dev.jombi.diverse.api.sns.dto

import dev.jombi.diverse.api.sns.dto.request.SNSModifyRequest
import dev.jombi.diverse.api.sns.dto.response.SNSListResponse
import dev.jombi.diverse.api.sns.dto.response.SNSResponse
import dev.jombi.diverse.business.sns.dto.SNSDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface SNSDtoMapper {
    @Component
    companion object {
        @Bean
        fun snsDtoMapper(): SNSDtoMapper = Mappers.getMapper(SNSDtoMapper::class.java)
    }


    fun convertToDto(data: SNSModifyRequest): SNSDto
    fun convertToResponse(data: SNSDto): SNSResponse

    @Mapping(source = "items", target = "snsList")
    fun convertToResponse(items: List<SNSDto>, dummy: Int = 0): SNSListResponse
}
