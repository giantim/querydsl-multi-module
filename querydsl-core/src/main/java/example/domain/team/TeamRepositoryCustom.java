package example.domain.team;

import java.util.List;

public interface TeamRepositoryCustom {
    List<String> findMemberNameInTeam(String teamName);
}
