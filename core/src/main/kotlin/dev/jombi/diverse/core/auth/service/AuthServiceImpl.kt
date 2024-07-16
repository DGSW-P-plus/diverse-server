package dev.jombi.diverse.core.auth.service

import dev.jombi.diverse.business.auth.dto.TokenDto
import dev.jombi.diverse.core.auth.extern.TokenGenerator
import dev.jombi.diverse.business.auth.service.AuthService
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.core.auth.exception.AuthExceptionDetails
import dev.jombi.diverse.core.member.entity.Member
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val memberRepository: MemberJpaRepository,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val tokenGenerator: TokenGenerator,
) : AuthService {
    override fun login(username: String, password: String): TokenDto {
        val token = UsernamePasswordAuthenticationToken(username, password)

        val auth = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext().authentication = auth

        val accessToken = tokenGenerator.generateAccessToken()
        val refreshToken = tokenGenerator.generateRefreshToken()

        return TokenDto(accessToken, refreshToken)
    }

    override fun signup(username: String, password: String, nickname: String): Long {
        if (memberRepository.existsByUsername(username))
            throw CustomException(AuthExceptionDetails.USER_ALREADY_EXISTS, username)

        return memberRepository.save(Member(
            username = username,
            password = passwordEncoder.encode(password),
            nickname = nickname
        )).id.id
    }

    override fun refresh(refreshToken: String): TokenDto {
        val newAccessToken = tokenGenerator.refreshToNewToken(refreshToken)
        return TokenDto(
            newAccessToken,
            refreshToken
        )
    }
}
