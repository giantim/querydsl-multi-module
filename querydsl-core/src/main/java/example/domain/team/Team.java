package example.domain.team;

import example.domain.BaseEntity;
import example.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(name = "id", column = @Column(name = "team_id"))
@Entity
public class Team extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        if (members.contains(member)) {
            return;
        }
        members.add(member);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void process() {
        this.name += "new";
    }

}
