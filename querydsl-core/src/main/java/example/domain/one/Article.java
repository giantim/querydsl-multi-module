package example.domain.one;

import example.domain.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AttributeOverride(name = "id", column = @Column(name = "article_id"))
@Entity
public class Article extends BaseEntity {
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Set<Comment> comments = new LinkedHashSet<>();

    public Article() {
    }

    public Article(String content) {
        this.content = content;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeAllComments() {
        comments.clear();
    }

    public Set<Comment> getComments() {
        return comments;
    }
}
