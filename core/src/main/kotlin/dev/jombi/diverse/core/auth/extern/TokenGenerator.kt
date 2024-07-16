package dev.jombi.diverse.core.auth.extern

interface TokenGenerator {
    fun generateAccessToken(): String
    fun generateRefreshToken(): String
    fun refreshToNewToken(refreshToken: String): String
}
