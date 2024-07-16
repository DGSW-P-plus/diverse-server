package dev.jombi.diverse.api.member.dto.response

data class MemberInfoResponse(
    val username: String,
    val password: String,
    val location: String?,
    val bio: String?
)