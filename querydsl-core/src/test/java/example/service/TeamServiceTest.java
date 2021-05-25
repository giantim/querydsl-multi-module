package example.service;

import example.domain.team.Team;
import example.domain.team.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    private void setUp() {
        teamRepository.save(new Team("team1"));
        teamRepository.save(new Team("team2"));
        teamRepository.save(new Team("team3"));
        teamRepository.save(new Team("team4"));
        teamRepository.save(new Team("team5"));
    }

    @Test
    void executeTest() {
        teamService.execute();

        List<Team> teams = teamRepository.findAll();
        teams.forEach(team -> {
            System.out.println("Team id: " + team.getId());
            System.out.println("Team name: " + team.getName());
            System.out.println();
        });
    }

    @AfterEach
    private void tearDown() {
        teamRepository.deleteAll();
    }

}
