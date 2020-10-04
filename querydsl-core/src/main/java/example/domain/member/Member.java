package example.domain.member;

import domain.MemberType;
import example.domain.BaseEntity;
import example.domain.team.Team;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@AttributeOverride(name = "id", column = @Column(name = "member_id"))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Member extends BaseEntity {
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Member(String name) {
        this.name = name;
    }

    public void addTeam(Team team) {
        this.team = team;
        team.addMember(this);
    }
}
