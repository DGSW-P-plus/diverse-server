package dev.jombi.diverse.api.member.dto.response

import dev.jombi.diverse.api.gender.dto.response.GenderResponse
import dev.jombi.diverse.api.sns.dto.response.SNSResponse

data class MemberInfoResponse(
    val id: Long,
    val username: String,
    val location: String?,
    val bio: String?,
    val genders: List<GenderResponse>,
    val sns: List<SNSResponse>
)
