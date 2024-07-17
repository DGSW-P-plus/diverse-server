package dev.jombi.diverse.core.gender.repository

import dev.jombi.diverse.core.gender.domain.entity.Gender
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenderJpaRepository : JpaRepository<Gender, Long> {
    fun existsByNameIs(name: String): Boolean
}

