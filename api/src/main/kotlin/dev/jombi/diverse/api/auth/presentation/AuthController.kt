package dev.jombi.diverse.api.auth.presentation

import dev.jombi.diverse.api.auth.dto.AuthDtoMapper
import dev.jombi.diverse.api.auth.dto.request.LoginRequest
import dev.jombi.diverse.api.auth.dto.request.ReissueRequest
import dev.jombi.diverse.api.auth.dto.request.SignUpRequest
import dev.jombi.diverse.api.auth.dto.response.TokenResponse
import dev.jombi.diverse.business.auth.service.AuthService
import dev.jombi.diverse.common.response.ResponseData
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth", description = "인증")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val authDtoMapper: AuthDtoMapper
) {
    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<ResponseData<TokenResponse>> {
        val dto = authService.login(request.username, request.password)
        return ResponseData.ok(data = authDtoMapper.convertToResponse(dto))
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest): ResponseEntity<ResponseData<TokenResponse>> {
        authService.signup(request.username, request.password, request.nickname)

        val dto = authService.login(request.username, request.password)
        return ResponseData.ok(data = authDtoMapper.convertToResponse(dto))
    }

    @Operation(summary = "토큰 재발급")
    @PostMapping("/reissue")
    fun reissue(@RequestBody @Valid request: ReissueRequest): ResponseEntity<ResponseData<TokenResponse>> {
        val dto = authService.refresh(request.refreshToken)
        return ResponseData.ok(data = authDtoMapper.convertToResponse(dto))
    }
}
