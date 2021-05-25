package example.domain;

import example.domain.team.Team;
import example.domain.team.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ContextTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    private void setUp() {
        teamRepository.save(new Team("team1"));
        teamRepository.save(new Team("team2"));
        teamRepository.save(new Team("team3"));
    }

    @Test
    void contextTest() {
        List<Team> teams = teamRepository.findAll();
        teams.get(2).changeName("new team 3");

        Team team = teamRepository.findByName("team2");
        if (Objects.nonNull(team)) {
            System.out.println(team.getName());
        } else {
            System.out.println("is null");
        }

        teams.get(0).changeName("new team 1");

        List<Team> changedTeams = teamRepository.findAll();
        assertThat(changedTeams.get(0).getName()).isEqualTo("new team 1");
        assertThat(changedTeams.get(2).getName()).isEqualTo("new team 3");
    }

    @Test
    void findByNameTest() {
        if (teamRepository.findAll().size() > 0) {
            Team team = teamRepository.findByName("team1");

            System.out.println(team.getName());
            assertThat(team.getName()).isEqualTo("team1");
        }
    }

    @AfterEach
    private void tearDown() {
        teamRepository.deleteAll();
    }

}
