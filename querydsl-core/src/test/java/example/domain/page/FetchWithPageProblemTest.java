package example.domain.page;

import example.domain.one.Article;
import example.domain.one.ArticleRepository;
import example.domain.one.Comment;
import example.domain.one.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FetchWithPageProblemTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    @Transactional
    public void setUp() {
        for (int i = 1; i <= 10; i++) {
            Article article = new Article(String.format("%d article", i));
            Article savedArticle = articleRepository.save(article);
            for (int j = 1; j <= 10; j++) {
                Comment comment = new Comment(String.format("%d article's %d comment", i, j));
                savedArticle.addComment(comment);
            }
            entityManager.flush();
        }
    }

    @DisplayName("fetch join과 pagenation을 같이 사용할 때 어떤 문제가 있는지 확인한다.")
    @Test
    @Transactional
    void fetchJoinWithPageTest() {
        entityManager.clear();
        List<Article> articles = articleRepository.findAll();

        assertThat(articles).hasSize(10);

        System.out.println("--------------");
        Set<Article> pageArticles = articleRepository.findArticleWithCommentLimitThree(3);

        assertThat(pageArticles).hasSize(3);
    }

    @AfterEach
    @Transactional
    void tearDown() {
        List<Article> articles = articleRepository.findAll();
        articles.forEach(Article::removeAllComments);
        entityManager.flush();

        commentRepository.deleteAll();
        articleRepository.deleteAll();
        entityManager.flush();
    }
}
