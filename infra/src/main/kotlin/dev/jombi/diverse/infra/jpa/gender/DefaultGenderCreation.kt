package dev.jombi.diverse.infra.jpa.gender

import dev.jombi.diverse.core.gender.domain.entity.Gender
import dev.jombi.diverse.core.gender.repository.GenderJpaRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class DefaultGenderCreation(
    private val genderJpaRepository: GenderJpaRepository
) {
    @PostConstruct
    fun addDefaultGenders() {
        val gen = listOf(
            Gender(
                "남성",
                "male.svg",
            ),
            Gender(
                "여성ㅐ",
                "female.svg",
            ),
            Gender(
                "에이젠더",
                "agender.svg",
            ),
            Gender(
                "안드로진",
                "androgyne.svg",
            ),
            Gender(
                "바이젠더",
                "bigender.svg",
            ),
            Gender(
                "젠더플루이드",
                "genderfluid.svg",
            ),
            Gender(
                "젠더퀴어",
                "genderqueer.svg",
            ),
            Gender(
                "인터섹스",
                "intersex.svg",
            ),
            Gender(
                "뉴트로이스",
                "neutrois.svg",
            ),
            Gender(
                "논바이너리",
                "nonbinary.svg",
            ),
            Gender(
                "팬젠더",
                "pangender.svg",
            ),
            Gender(
                "트랜스젠더",
                "transgender.svg",
            ),
            Gender(
                "트라이젠더",
                "trigender.svg",
            ),
            Gender(
                "제노젠더",
                "xenogender.svg",
            ),
        )

        genderJpaRepository.saveAll(gen.filterNot { genderJpaRepository.existsByNameIs(it.name) })
    }
}
