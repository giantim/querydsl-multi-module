package example.domain;

import example.domain.team.Team;
import example.domain.team.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Sql("/memberWithTeam.sql")
public class FailedTest {

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("@DataJpa 어노테이션 사용 시 테스트에서 항상 같은 트랜잭션을 공유하기 때문에 @SpringBootTest로 테스트하기")
    @Test
    @Transactional
    void showMemeber() {
        Team team = teamRepository.findAll().get(0);
        team.getMembers().stream()
                .forEach(member -> System.out.println(member.getName()));
    }
}
