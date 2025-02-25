package dev.jombi.diverse.infra.security.jwt.filter

import dev.jombi.diverse.infra.security.jwt.JwtAuthToken
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(@Lazy private val authManager: AuthenticationManager) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")?.removePrefix("Bearer")?.trim()
            ?.takeIf { it.isNotEmpty() }

        if (jwt != null) {
            println(jwt)
            val auth = authManager.authenticate(JwtAuthToken(jwt))
            SecurityContextHolder.getContext().authentication = auth
        }

        chain.doFilter(request, response)
    }
}
