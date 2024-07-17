package dev.jombi.diverse.core.member.repository

import dev.jombi.diverse.core.member.domain.entity.Member
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface MemberQueryRepository {
    fun justPaginationIgnoreSelf(info: Pageable, self: Member): Slice<Member>
}
