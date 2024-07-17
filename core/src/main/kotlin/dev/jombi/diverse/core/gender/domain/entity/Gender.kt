package dev.jombi.diverse.core.gender.domain.entity

import dev.jombi.diverse.business.gender.dto.GenderDto
import dev.jombi.diverse.core.common.entity.BaseIdEntity
import dev.jombi.diverse.core.common.entity.FetchableId
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "tb_gender")
class Gender(
    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = true)
    val iconName: String? = null,

    id: FetchableId = FetchableId.NULL
) : BaseIdEntity(id) {
    fun mapDto() = GenderDto(name, iconName, id.id)
}
