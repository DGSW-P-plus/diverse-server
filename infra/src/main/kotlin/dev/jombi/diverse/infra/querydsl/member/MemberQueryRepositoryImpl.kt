package dev.jombi.diverse.infra.querydsl.member

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.jombi.diverse.core.gender.domain.entity.QMemberGender.memberGender
import dev.jombi.diverse.core.member.domain.entity.Member
import dev.jombi.diverse.core.member.domain.entity.QMember.member
import dev.jombi.diverse.core.member.repository.MemberQueryRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : MemberQueryRepository {
    override fun justPaginationIgnoreSelf(info: Pageable, self: Member): Slice<Member> {
        val a = jpaQueryFactory
            .selectFrom(member)
            .leftJoin(memberGender)
            .on(memberGender.id.memberId.eq(member.id))
            .where(member.id.ne(self.id.id))
            .offset(info.offset)
            .limit(info.pageSize + 1L)
//            .orderBy(Expressions.stringTemplate("FIELD({0}, {1})", memberGender.id.genderId, 1, 6).desc())
            .fetch()


        var hasNext = false
        if (a.size > info.pageSize) {
            a.removeAt(info.pageSize)
            hasNext = true
        }

        return SliceImpl(a, info, hasNext)
    }
}
