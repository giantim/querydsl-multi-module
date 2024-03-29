package example.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    Team findByName(String name);

//    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    List<Team> findAllByIdIn(List<Long> ids);

}
