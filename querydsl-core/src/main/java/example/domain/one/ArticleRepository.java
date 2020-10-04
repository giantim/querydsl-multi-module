package example.domain.one;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Query("select a from Article a join fetch a.comments")
    Set<Article> findALlJoinFetch();

    @EntityGraph(attributePaths = "comments")
    @Query("select a from Article a")
    List<Article> findAllEntityGraph();
}
