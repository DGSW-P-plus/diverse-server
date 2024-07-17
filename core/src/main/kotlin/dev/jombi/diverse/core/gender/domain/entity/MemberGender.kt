package dev.jombi.diverse.core.gender.domain.entity

import dev.jombi.diverse.core.member.domain.entity.Member
import jakarta.persistence.*

@Entity(name = "tb_gender_member")
class MemberGender(
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("_genderId")
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    val gender: Gender,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("_memberId")
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    val member: Member,

    @EmbeddedId
    val id: MemberGenderKey = MemberGenderKey(),
)
