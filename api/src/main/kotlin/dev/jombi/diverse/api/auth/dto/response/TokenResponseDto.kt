package dev.jombi.diverse.api.auth.dto.response

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
