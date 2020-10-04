package example.domain.one;

import java.util.Set;

public interface ArticleRepositoryCustom {
    Set<Article> findArticleWithCommentLimitThree(int pageCount);
}
