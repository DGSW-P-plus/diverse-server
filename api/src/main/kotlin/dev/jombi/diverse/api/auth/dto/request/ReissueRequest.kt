package dev.jombi.diverse.api.auth.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

data class ReissueRequest @JsonCreator constructor(
    val refreshToken: String
)
