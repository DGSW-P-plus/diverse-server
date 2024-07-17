package dev.jombi.diverse.business.member.dto

import dev.jombi.diverse.business.gender.dto.GenderDto

data class MemberDto(
    val id: Long,
    val username: String,
    val nickname: String,
    val location: String?,
    val bio: String?,
    val genders: List<GenderDto>
)
