package dev.jombi.diverse.core.gender.repository

import dev.jombi.diverse.core.gender.domain.entity.Gender

interface MemberGenderQueryRepository {
    fun findGenderByUserId(userId: Long): List<Gender>
}
