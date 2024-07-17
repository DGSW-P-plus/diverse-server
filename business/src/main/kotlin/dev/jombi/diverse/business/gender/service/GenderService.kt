package dev.jombi.diverse.business.gender.service

import dev.jombi.diverse.business.gender.dto.GenderDto

interface GenderService {
    fun getGenders(): List<GenderDto>
    fun myGenders(): List<GenderDto>
    fun setMemberGenders(set: List<Long>)
}
