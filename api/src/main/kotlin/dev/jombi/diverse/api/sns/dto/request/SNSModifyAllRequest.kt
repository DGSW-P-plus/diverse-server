package dev.jombi.diverse.api.sns.dto.request

import com.fasterxml.jackson.annotation.JsonCreator

data class SNSModifyAllRequest @JsonCreator constructor(
    val sns: List<SNSModifyRequest>
)
