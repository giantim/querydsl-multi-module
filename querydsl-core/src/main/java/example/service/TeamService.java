package example.service;

import example.domain.team.Team;
import example.domain.team.TeamQueryRepository;
import example.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Transactional
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamQueryRepository teamQueryRepository;

    public void execute() {
        List<Long> teamIds = Arrays.asList(1L, 2L);
        List<Team> teams = teamQueryRepository.findAllByIdIn(teamIds);
        teams.forEach(Team::process);
    }

}
