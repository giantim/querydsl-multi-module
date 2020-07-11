package example.domain.member;

import domain.MemberType;
import example.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public Member(String name) {
        this.name = name;
    }
}
