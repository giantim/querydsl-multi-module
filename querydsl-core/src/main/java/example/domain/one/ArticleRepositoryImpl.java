package example.domain.one;

import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static example.domain.one.QArticle.article;
import static example.domain.one.QComment.comment;

public class ArticleRepositoryImpl implements ArticleRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    ArticleRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Set<Article> findArticleWithCommentLimitThree(int pageCount) {
        List<Article> articles = jpaQueryFactory.selectFrom(article)
                .innerJoin(article.comments, comment)
                .fetchJoin()
                .limit(pageCount)
                .fetch();
        Set<Article> unmodifiableArticles = new LinkedHashSet<>(articles);
        return unmodifiableArticles;
    }
}
