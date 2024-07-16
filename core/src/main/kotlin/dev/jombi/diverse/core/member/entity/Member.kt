package dev.jombi.diverse.core.member.entity

import dev.jombi.diverse.core.common.entity.BaseIdTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_member")
data class Member(
    @Column(unique = true, nullable = false)
    val credential: String,

    @Column(nullable = false)
    val password: String, // bcrypt

    @Column(nullable = false)
    val name: String,

    @Column
    val location: String? = null,

    @Column(length = 512)
    val bio: String? = null
) : BaseIdTimeEntity()
