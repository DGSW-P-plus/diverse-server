package dev.jombi.diverse.api.member.dto.response

data class MemberInfoResponse(
    val id: Long,
    val username: String,
    val location: String?,
    val bio: String?
)