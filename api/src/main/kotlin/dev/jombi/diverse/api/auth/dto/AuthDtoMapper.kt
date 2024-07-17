package dev.jombi.diverse.api.auth.dto

import dev.jombi.diverse.api.auth.dto.response.TokenResponse
import dev.jombi.diverse.business.auth.dto.TokenDto
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Mapper
interface AuthDtoMapper {
    @Component
    companion object {
        @Bean
        fun authInstance(): AuthDtoMapper = Mappers.getMapper(AuthDtoMapper::class.java)
    }

    fun convertToResponse(request: TokenDto): TokenResponse
}
