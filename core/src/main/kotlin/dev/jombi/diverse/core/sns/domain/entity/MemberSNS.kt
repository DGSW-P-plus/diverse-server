package dev.jombi.diverse.core.sns.domain.entity

import dev.jombi.diverse.core.common.entity.BaseIdEntity
import dev.jombi.diverse.core.member.domain.entity.Member
import dev.jombi.diverse.core.sns.domain.consts.SNSType
import jakarta.persistence.*

@Entity
class MemberSNS(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    val member: Member,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: SNSType,

    @Column(nullable = false)
    val url: String,
) : BaseIdEntity()
