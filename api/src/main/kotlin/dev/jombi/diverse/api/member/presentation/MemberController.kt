package dev.jombi.diverse.api.member.presentation

import dev.jombi.diverse.api.member.dto.response.MemberInfoResponse
import dev.jombi.diverse.business.member.service.MemberService
import dev.jombi.diverse.common.response.ResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberInfoResponse>> {
        val me = memberService.me()

        return ResponseData.ok(data = MemberInfoResponse(me.name))
    }
}
