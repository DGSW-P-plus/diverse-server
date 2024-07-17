package dev.jombi.diverse.api.member.presentation

import dev.jombi.diverse.api.member.dto.MemberDtoMapper
import dev.jombi.diverse.api.member.dto.request.MemberEditOptionalRequest
import dev.jombi.diverse.api.member.dto.response.MemberInfoResponse
import dev.jombi.diverse.business.member.service.MemberService
import dev.jombi.diverse.common.response.ResponseData
import dev.jombi.diverse.common.response.ResponseEmpty
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member", description = "멤버")
@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService,
    private val memberDtoMapper: MemberDtoMapper
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberInfoResponse>> {
        val me = memberService.me()

        return ResponseData.ok(data = memberDtoMapper.convertToResponse(me))
    }

    @PatchMapping("/me")
    fun editMyInfo(@RequestBody @Valid request: MemberEditOptionalRequest): ResponseEntity<ResponseEmpty> {
        memberService.editMyInfo(memberDtoMapper.convertToDto(request))
        return ResponseEmpty.ok()
    }
}
