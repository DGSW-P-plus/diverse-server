package dev.jombi.diverse.core.member.domain.details

import dev.jombi.diverse.core.member.domain.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(val member: Member) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableSetOf()
    override fun getPassword(): String = member.password
    override fun getUsername(): String = member.username
}
