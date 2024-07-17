package dev.jombi.diverse.business.gender.service

import dev.jombi.diverse.business.gender.dto.GenderDto

interface GenderService {
    fun all(): List<GenderDto>
    fun my(): List<GenderDto>
    fun setGenders(set: List<Long>)
}
