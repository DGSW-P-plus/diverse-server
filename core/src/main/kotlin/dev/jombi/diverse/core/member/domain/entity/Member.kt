package dev.jombi.diverse.core.member.domain.entity

import dev.jombi.diverse.core.common.entity.BaseIdTimeEntity
import dev.jombi.diverse.core.common.entity.FetchableId
import jakarta.persistence.*

@Entity(name = "tb_member")
class Member(
    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String, // bcrypt

    @Column(nullable = false)
    var nickname: String,

    @Column
    var location: String? = null,

    @Column(length = 512)
    var bio: String? = null,

    id: FetchableId = FetchableId.NULL,
) : BaseIdTimeEntity(id = id)
