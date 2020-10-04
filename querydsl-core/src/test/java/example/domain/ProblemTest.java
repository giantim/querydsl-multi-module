package example.domain;

import example.domain.member.Member;
import example.domain.team.Team;
import example.domain.team.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/memberWithTeam.sql")
@SpringBootTest
public class ProblemTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("N+1 문제를 일으키는 쿼리문을 확인한다.")
    @Transactional
    @Test
    void nPlusOneProblemTest() {
        entityManager.clear();

        Team woowa = teamRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new);
        List<Member> members = woowa.getMembers();

        assertThat(woowa).isInstanceOf(Team.class);
        assertThat(members).hasSize(4);
    }
}
