package example.domain.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import example.domain.member.dto.MemberDto;

import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    public MemberRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Member findByName(String name) {
        return jpaQueryFactory.selectFrom(member)
                .where(member.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<MemberDto> findMembersByAgeOver(int age) {
        return jpaQueryFactory.select(Projections.fields(MemberDto.class, member.name, member.age))
                .from(member)
                .where(member.age.goe(age))
                .fetch();
    }
}
