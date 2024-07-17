package dev.jombi.diverse.business.member.dto

data class HasNextMemberDto(
    val hasNext: Boolean,
    val members: List<MemberDto>
)
