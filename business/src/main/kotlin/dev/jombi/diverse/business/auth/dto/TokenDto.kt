package dev.jombi.diverse.business.auth.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
