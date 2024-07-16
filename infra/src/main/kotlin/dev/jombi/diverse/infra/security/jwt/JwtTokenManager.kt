package dev.jombi.diverse.infra.security.jwt

import dev.jombi.diverse.core.auth.extern.TokenGenerator
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.common.exception.GlobalExceptionDetail
import dev.jombi.diverse.core.auth.exception.AuthExceptionDetails
import dev.jombi.diverse.core.member.MemberHolder
import dev.jombi.diverse.infra.security.jwt.enums.JwtType
import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenManager(
    private val jwtProperties: JwtProperties,
    private val memberHolder: MemberHolder,
    private val memberDetailsService: UserDetailsService
) : TokenGenerator, TokenValidator {
    override fun generateAccessToken(): String {
        return generateToken(JwtType.ACCESS_TOKEN)
    }

    override fun generateRefreshToken(): String {
        return generateToken(JwtType.REFRESH_TOKEN)
    }

    private fun generateToken(type: JwtType): String {
        val date = Date()
        return Jwts.builder()
            .header()
            .type(type.name)
            .and()
            .signWith(jwtProperties.secretKey(), Jwts.SIG.HS512)
            .issuer(jwtProperties.issuer)
            .subject(memberHolder.get().username)
            .issuedAt(date)
            .expiration(date.apply { time += getExpiration(type) })
            .compact()
    }

    fun getExpiration(type: JwtType): Long {
        return when (type) {
            JwtType.ACCESS_TOKEN -> jwtProperties.accessTokenExpiration
            JwtType.REFRESH_TOKEN -> jwtProperties.refreshTokenExpiration
        }
    }

    override fun refreshToNewToken(refreshToken: String): String {
        val parsed = parse(refreshToken)
        if (JwtType.valueOf(parsed.header.type) != JwtType.REFRESH_TOKEN)
            throw CustomException(AuthExceptionDetails.TOKEN_TYPE_MISMATCH)

        val pl = (parsed.payload as Claims)

        // in this case, context is unauthorized. so trust the refresh token (sadly)
        SecurityContextHolder.getContext().authentication = authenticate(pl)

        return generateAccessToken()
    }

    private val jwtParser = Jwts.parser()
        .verifyWith(jwtProperties.secretKey())
        .requireIssuer(jwtProperties.issuer)
        .build()

    fun authenticate(payload: Claims): Authentication {
        val member = memberDetailsService.loadUserByUsername(payload.subject)
        return  UsernamePasswordAuthenticationToken(member, null, setOf())
    }

    override fun validate(jwt: String): Authentication {
        val parsed = parse(jwt)

        val pl = parsed.payload as Claims

        if (JwtType.valueOf(parsed.header.type) != JwtType.ACCESS_TOKEN)
            throw CustomException(AuthExceptionDetails.TOKEN_TYPE_MISMATCH)

        return authenticate(pl)
    }

    private fun parse(token: String): Jwt<*, *> {
        try {
            val parsed = jwtParser.parse(token)
            if (parsed.payload !is Claims)
                throw MalformedJwtException("no claims (raw)") // validation

            return parsed
        } catch (e: ExpiredJwtException) {
            throw CustomException(AuthExceptionDetails.EXPIRED_TOKEN)
        } catch (e: MalformedJwtException) {
            throw CustomException(AuthExceptionDetails.INVALID_TOKEN)
        } catch (e: CustomException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw CustomException(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
        }
    }
}
