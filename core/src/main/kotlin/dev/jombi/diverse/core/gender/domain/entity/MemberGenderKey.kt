package dev.jombi.diverse.core.gender.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class MemberGenderKey(
    @Column(name = "gender_id")
    val genderId: Long? = null,
    @Column(name = "member_id")
    val memberId: Long? = null
) : Serializable {
}
