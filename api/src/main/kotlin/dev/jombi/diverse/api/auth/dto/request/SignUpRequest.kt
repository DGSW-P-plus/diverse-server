package dev.jombi.diverse.api.auth.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    @field:Size(min = 8)
    val password: String,

    @field:NotBlank
    val nickname: String
)
