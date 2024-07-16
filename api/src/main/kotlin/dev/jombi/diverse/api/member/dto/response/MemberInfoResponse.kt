package dev.jombi.diverse.api.member.dto.response

data class MemberInfoResponse(
    val credential: String,
    val name: String,
    val location: String?,
    val bio: String?
)