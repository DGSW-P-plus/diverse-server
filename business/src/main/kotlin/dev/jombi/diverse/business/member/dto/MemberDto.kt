package dev.jombi.diverse.business.member.dto

data class MemberDto(
    val id: Long,
    val username: String,
    val nickname: String,
    val location: String?,
    val bio: String?
)
