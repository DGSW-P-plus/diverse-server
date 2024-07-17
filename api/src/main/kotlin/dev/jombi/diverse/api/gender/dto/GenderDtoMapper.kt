package dev.jombi.diverse.api.gender.dto

import dev.jombi.diverse.api.gender.dto.response.GenderListResponse
import dev.jombi.diverse.api.gender.dto.response.GenderResponse
import dev.jombi.diverse.business.gender.dto.GenderDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface GenderDtoMapper {
    @Component
    companion object {
        @Bean
        fun genderInstance(): GenderDtoMapper = Mappers.getMapper(GenderDtoMapper::class.java)
    }

    fun convertToResponse(data: GenderDto): GenderResponse

    @Mapping(source = "items", target = "genders")
    fun convertToResponse(items: List<GenderDto>, dummy: Int = 0): GenderListResponse
}
