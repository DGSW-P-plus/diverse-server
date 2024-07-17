package dev.jombi.diverse.api.member.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MemberEditOptionalRequest(
    @Length(max = 255)
    @NotBlank
    val nickname: String? = null,
    @Length(max = 512)
    @NotBlank
    val bio: String? = null,
    @Length(max = 255)
    @NotBlank
    val location: String? = null
)
