package dev.jombi.diverse.infra.querydsl.member

import com.querydsl.core.types.Projections
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import dev.jombi.diverse.business.gender.dto.GenderDto
import dev.jombi.diverse.business.member.dto.MemberDto
import dev.jombi.diverse.business.sns.dto.SNSDto
import dev.jombi.diverse.core.gender.domain.entity.Gender
import dev.jombi.diverse.core.gender.domain.entity.QGender.gender
import dev.jombi.diverse.core.gender.domain.entity.QMemberGender.memberGender
import dev.jombi.diverse.core.member.domain.entity.Member
import dev.jombi.diverse.core.member.domain.entity.QMember.member
import dev.jombi.diverse.core.member.repository.MemberQueryRepository
import dev.jombi.diverse.core.sns.domain.entity.QMemberSNS.memberSNS
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository

@Repository
class MemberQueryRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : MemberQueryRepository {
    override fun findAllWithSortingAndPagination(pageable: Pageable, genders: List<Gender>): Slice<MemberDto> {
        val m = jpaQueryFactory
            .select(
                Projections.constructor(
                    MemberDto::class.java,
                    member.id,
                    member.username,
                    member.nickname,
                    member.location,
                    member.bio,
                    Projections.list(gender),
                    Projections.list(memberSNS)
                )
            )
            .from(member)

            .innerJoin(gender)
            .innerJoin(memberGender)
            .on(memberGender.gender.`in`(genders))

//            .on(gender.id.eq(memberGender.id.genderId))

            .innerJoin(memberSNS)
            .on(memberSNS.member.id.eq(member.id))

            .orderBy()

            .offset(pageable.offset)
            .limit(pageable.pageSize + 1L)
            .fetch()


        var hasNext = false
        if (m.size > pageable.pageSize) {
            m.removeAt(pageable.pageSize)
            hasNext = true
        }


        return SliceImpl(m, pageable, hasNext)
    }

    override fun findOneWithMember(user: Member): MemberDto {
        println(user.id)
        println(user.nickname)
        println(user.username)
        println(user.bio)
        TODO()
//        val lst = jpaQueryFactory
//            .select(
//                Projections.constructor(
//                    MemberDto::class.java,
//                    member.id,
//                    member.username,
//                    member.nickname,
//                    member.location,
//                    member.bio,
//                    Projections.list(
////                        Projections.constructor(
////                            GenderDto::class.java,
////                            gender.name,
////                            gender.iconName,
////                            gender.id
////                        )
//                    ),
//                    Projections.list(
////                        Expressions.nullExpression()
////                        Projections.constructor(
////                            SNSDto::class.java,
////                            memberSNS.url,
////                            memberSNS.type.stringValue()
////                        )
//                    )
//                )
//            )
//            .from(member)
//
////            .innerJoin(memberGender)
////            .on(memberGender.id.memberId.eq(member.id))
////            .innerJoin(gender)
////            .on(memberGender.id.memberId.eq(gender.id))
////            .innerJoin(memberSNS)
////            .on(memberSNS.member.id.eq(member.id))
//
////            .where(member.username.eq(user.username))
//
//            .fetch()
//
//        println(lst.joinToString("\n"))
//
//        return lst.first()
    }
}
