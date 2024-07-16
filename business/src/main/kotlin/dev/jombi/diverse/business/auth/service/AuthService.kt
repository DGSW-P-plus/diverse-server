package dev.jombi.diverse.business.auth.service

import dev.jombi.diverse.business.auth.dto.TokenDto

interface AuthService {
    fun login(credential: String, password: String): TokenDto
    fun signup(name: String, credential: String, password: String): Long
    fun refresh(refreshToken: String): TokenDto
}
