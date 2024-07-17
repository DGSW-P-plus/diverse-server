package dev.jombi.diverse.infra.querydsl.gender

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.jombi.diverse.core.gender.domain.entity.Gender
import dev.jombi.diverse.core.gender.domain.entity.QGender.gender
import dev.jombi.diverse.core.gender.domain.entity.QMemberGender.memberGender
import dev.jombi.diverse.core.gender.repository.MemberGenderQueryRepository
import org.springframework.stereotype.Repository

@Repository
class MemberGenderQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : MemberGenderQueryRepository {
    override fun findGenderByUserId(userId: Long): List<Gender> {
        return queryFactory
            .select(gender)
            .from(memberGender)
            .innerJoin(gender)
            .on(memberGender.id.genderId.eq(gender.id))
            .where(memberGender.id.memberId.eq(userId))
            .fetch()
    }
}
