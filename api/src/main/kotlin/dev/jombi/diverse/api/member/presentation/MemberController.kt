package dev.jombi.diverse.api.member.presentation

import dev.jombi.diverse.api.member.dto.response.MemberInfoResponse
import dev.jombi.diverse.business.member.service.MemberService
import dev.jombi.diverse.common.response.ResponseData
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Member", description = "멤버")
@RestController
@RequestMapping("/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<MemberInfoResponse>> {
        val me = memberService.me()

        return ResponseData.ok(data = MemberInfoResponse(credential = me.credential, name = me.name, location = me.location, bio = me.bio))
    }
}
