package dev.jombi.diverse.business.member.dto

data class MemberDto(
    val credential: String,
    val name: String,
    val location: String?,
    val bio: String?
)
