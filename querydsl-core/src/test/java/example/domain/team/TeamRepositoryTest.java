package example.domain.team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    private void setUp() {
        teamRepository.save(new Team("team1"));
        teamRepository.save(new Team("team2"));
        teamRepository.save(new Team("team3"));
    }

    @Test
    void findAllByIdInTest() {
        List<Long> teamIds = Arrays.asList(1L, 2L);

        List<Team> teams = teamRepository.findAllByIdIn(teamIds);

        assertThat(teams).hasSize(2);
        assertThat(teams.get(0).getName()).isEqualTo("team1");
    }

}
