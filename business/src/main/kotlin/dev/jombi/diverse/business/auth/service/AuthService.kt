package dev.jombi.diverse.business.auth.service

import dev.jombi.diverse.business.auth.dto.TokenDto

interface AuthService {
    fun login(username: String, password: String): TokenDto
    fun signup(username: String, password: String, nickname: String): Long
    fun refresh(refreshToken: String): TokenDto
}
