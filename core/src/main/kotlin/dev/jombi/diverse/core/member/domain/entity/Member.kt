package dev.jombi.diverse.core.member.domain.entity

import dev.jombi.diverse.core.common.entity.BaseIdTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_member")
class Member(
    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String, // bcrypt

    @Column(nullable = false)
    val nickname: String,

    @Column
    val location: String? = null,

    @Column(length = 512)
    val bio: String? = null
) : BaseIdTimeEntity()
