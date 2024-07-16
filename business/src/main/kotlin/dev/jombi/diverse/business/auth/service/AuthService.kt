package dev.jombi.diverse.business.auth.service

import dev.jombi.diverse.business.auth.dto.TokenDto

interface AuthService {
    fun authenticate(credential: String, password: String): TokenDto
    fun createNewMember(name: String, credential: String, password: String): Long
    fun getNewToken(refreshToken: String): TokenDto
}
