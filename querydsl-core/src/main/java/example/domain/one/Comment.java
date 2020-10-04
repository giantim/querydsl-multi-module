package example.domain.one;

import example.domain.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@AttributeOverride(name = "id", column = @Column(name = "comment_id"))
@Entity
public class Comment extends BaseEntity {
    private String comments;

    public Comment() {
    }

    public Comment(String comments) {
        this.comments = comments;
    }
}
