package dev.jombi.diverse.infra.security.details

import dev.jombi.diverse.core.member.domain.details.MemberDetails
import dev.jombi.diverse.core.member.repository.MemberJpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberDetailsService(
    private val memberRepository: MemberJpaRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByUsername(username)
            ?: throw UsernameNotFoundException(username)

        return MemberDetails(member)
    }
}
