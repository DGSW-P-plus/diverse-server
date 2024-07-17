package dev.jombi.diverse.api.sns.presentation

import dev.jombi.diverse.api.sns.dto.SNSDtoMapper
import dev.jombi.diverse.api.sns.dto.request.SNSModifyRequest
import dev.jombi.diverse.api.sns.dto.response.SNSListResponse
import dev.jombi.diverse.business.sns.service.SNSService
import dev.jombi.diverse.common.response.ResponseData
import dev.jombi.diverse.common.response.ResponseEmpty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sns")
class SNSController(
    private val snsService: SNSService,
    private val snsDtoMapper: SNSDtoMapper
) {
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseData<SNSListResponse>> {
        val my = snsService.getMySNSList()
        return ResponseData.ok(data = snsDtoMapper.convertToResponse(my))
    }

    @PatchMapping("/me")
    fun modifyMySNS(@RequestBody request: SNSModifyRequest): ResponseEntity<ResponseEmpty> {
        snsService.addSNS(snsDtoMapper.convertToDto(request))
        return ResponseEmpty.ok()
    }
}
