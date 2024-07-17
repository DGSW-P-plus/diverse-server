package dev.jombi.diverse.core.sns.repository

import dev.jombi.diverse.core.sns.domain.entity.MemberSNS
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberSNSJpaRepository : JpaRepository<MemberSNS, Long>
