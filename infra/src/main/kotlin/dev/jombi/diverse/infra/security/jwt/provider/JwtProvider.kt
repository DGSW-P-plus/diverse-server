package dev.jombi.diverse.infra.security.jwt.provider

import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.common.exception.GlobalExceptionDetail
import dev.jombi.diverse.infra.security.details.MemberDetailsService
import dev.jombi.diverse.infra.security.jwt.JwtAuthToken
import dev.jombi.diverse.infra.security.jwt.JwtProperties
import dev.jombi.diverse.infra.security.jwt.TokenValidator
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    private val validator: TokenValidator,
    private val jwtProperties: JwtProperties,
    private val memberDetailsService: MemberDetailsService
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val jwt = authentication?.credentials?.toString()
            ?: throw CustomException(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)

        return validator.validate(jwt)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthToken::class.java == authentication
    }

    fun getAuthentication(token: String): Authentication {
        val username = Jwts.parser().verifyWith(jwtProperties.secretKey()).build().parseSignedClaims(token).payload.subject
        val details = memberDetailsService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(details, null, details.authorities)
    }
}
