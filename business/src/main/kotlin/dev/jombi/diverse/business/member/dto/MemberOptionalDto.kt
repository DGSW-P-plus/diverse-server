package dev.jombi.diverse.business.member.dto

data class MemberOptionalDto(
    val username: String?,
    val nickname: String?,
    val location: String?,
    val bio: String?
)
