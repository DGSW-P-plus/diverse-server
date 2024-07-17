package dev.jombi.diverse.api.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val nickname: String
)
