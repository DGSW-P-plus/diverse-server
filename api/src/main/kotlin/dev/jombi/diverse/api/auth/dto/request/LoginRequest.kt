package dev.jombi.diverse.api.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank
    val credential: String,

    @field:NotBlank
    val password: String
)
