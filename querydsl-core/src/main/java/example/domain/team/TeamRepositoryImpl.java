package example.domain.team;

import com.querydsl.jpa.impl.JPAQueryFactory;
import example.domain.member.QMember;

import java.util.List;

public class TeamRepositoryImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QTeam team = QTeam.team;
    private final QMember member = QMember.member;

    public TeamRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<String> findMemberNameInTeam(String teamName) {
        return jpaQueryFactory.select(member.name)
                .from(team)
                .innerJoin(member).on(team.id.eq(member.team.id))
                .where(team.name.eq(teamName))
                .fetch();
    }
}
