package example.domain.one;

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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NPlusOneProblemTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    private Long articleId;

    @BeforeEach
    @Transactional
    void setUp() {
        Article firstArticle = new Article("first article");
        Article savedFirstArticle = articleRepository.save(firstArticle);
        articleId = savedFirstArticle.getId();

        Comment firstComment = commentRepository.save(new Comment("first comment"));
        Comment secondComment = commentRepository.save(new Comment("second comment"));
        Comment thirdComment = commentRepository.save(new Comment("third comment"));

        savedFirstArticle.addComment(firstComment);
        savedFirstArticle.addComment(secondComment);
        savedFirstArticle.addComment(thirdComment);

        Article secondArticle = new Article("second article");
        Article savedSecondArticle = articleRepository.save(secondArticle);
        articleId = savedFirstArticle.getId();

        Comment fourthComment = commentRepository.save(new Comment("fourth comment"));
        Comment fifthComment = commentRepository.save(new Comment("fifth comment"));
        Comment sixthComment = commentRepository.save(new Comment("sixth comment"));

        savedSecondArticle.addComment(fourthComment);
        savedSecondArticle.addComment(fifthComment);
        savedSecondArticle.addComment(sixthComment);

        entityManager.flush();
    }

    @DisplayName("지연 로딩 시 n+1 문제를 확인해본다.")
    @Test
    @Transactional
    void nPlusOneProblemTest() {
        entityManager.clear();
        System.out.println("------------------");
        List<Article> articles = articleRepository.findAll();
        List<Comment> comments = articles.stream()
                .flatMap(article -> article.getComments().stream())
                .collect(Collectors.toList());

        assertThat(comments).hasSize(6);
    }

    @DisplayName("join fetch를 이용해 n+1 문제를 해결한다.")
    @Test
    @Transactional
    void nPlusOneProblemSolveWithJoinFetchTest() {
        entityManager.clear();
        System.out.println("--------------join fetch-----------------");

        Set<Article> articles = articleRepository.findALlJoinFetch();

        assertThat(articles).hasSize(2);
    }

    @DisplayName("entity graph를 이용해 n+1 문제를 해결한다.")
    @Test
    @Transactional
    void nPlusOneProblemSolveWithEntityGraphTest() {
        entityManager.clear();
        System.out.println("--------------entity graph-----------------");

        List<Article> articles = articleRepository.findAllEntityGraph();

        assertThat(articles).hasSize(2); // 왜 곱 만큼 안나오지? -> Hibernate가 버전이 오르면서 기본 기능이 바뀌었나?
    }

    @AfterEach
    @Transactional
    void tearDown() {
        System.out.println("---------------------------");
        List<Article> articles = articleRepository.findAll();
        articles.forEach(Article::removeAllComments);
        entityManager.flush();

        commentRepository.deleteAll();
        articleRepository.deleteAll();
        entityManager.flush();
    }
}
