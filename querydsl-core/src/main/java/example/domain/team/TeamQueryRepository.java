package example.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public interface TeamQueryRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByIdIn(List<Long> ids);

}
