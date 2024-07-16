package dev.jombi.diverse.infra.security.jwt

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@ConfigurationProperties(prefix = "app.jwt")
class JwtProperties(
    private val secret: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long,
    val issuer: String
) {
    private var _key: SecretKey? = null

    @OptIn(ExperimentalEncodingApi::class)
    fun secretKey(): SecretKey = _key ?: Keys.hmacShaKeyFor(Base64.decode(secret))
}
