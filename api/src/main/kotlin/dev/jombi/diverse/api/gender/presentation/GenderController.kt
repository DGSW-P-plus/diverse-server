package dev.jombi.diverse.api.gender.presentation

import dev.jombi.diverse.api.gender.dto.request.SetMyGenderRequest
import dev.jombi.diverse.api.gender.dto.response.GenderListResponse
import dev.jombi.diverse.business.gender.service.GenderService
import dev.jombi.diverse.common.response.ResponseData
import dev.jombi.diverse.common.response.ResponseEmpty
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/genders")
class GenderController(
    private val genderService: GenderService
) {
    @GetMapping
    fun getAll(): ResponseEntity<ResponseData<GenderListResponse>> {
        val all = genderService.all()
        return ResponseData.ok(data = GenderListResponse(all))
    }

    @GetMapping("/my")
    fun getMy(): ResponseEntity<ResponseData<GenderListResponse>> {
        val my = genderService.my()
        return ResponseData.ok(data = GenderListResponse(my))
    }

    @PutMapping("/my")
    fun setMy(@RequestBody @Valid request: SetMyGenderRequest): ResponseEntity<ResponseEmpty> {
        genderService.setGenders(request.genderIds)

        return ResponseEmpty.ok()
    }

}
