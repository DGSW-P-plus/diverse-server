package dev.jombi.diverse.api.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
    @field:NotBlank
    val credential: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val name: String
)
