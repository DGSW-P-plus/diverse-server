package dev.jombi.diverse.api.gender.dto.request

import com.fasterxml.jackson.annotation.JsonCreator
import jakarta.validation.constraints.NotEmpty

class SetMyGenderRequest @JsonCreator constructor(
    @NotEmpty
    val genderIds: List<Long>
)
