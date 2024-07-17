package dev.jombi.diverse.business.member.dto

import dev.jombi.diverse.business.gender.dto.GenderDto
import dev.jombi.diverse.business.sns.dto.SNSDto

data class MemberDto(
    val id: Long,
    val username: String,
    val nickname: String,
    val location: String?,
    val bio: String?,
    val genders: List<GenderDto>,
    val sns: List<SNSDto>
)
