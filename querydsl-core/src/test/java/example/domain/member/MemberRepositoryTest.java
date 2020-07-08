package example.domain.member;

import example.config.QuerydslConfiguration;
import example.domain.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfiguration.class)
@Sql("/member.sql")
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByNameTest() {
        Member member = memberRepository.findByName("lavine");

        assertThat(member).isInstanceOf(Member.class);
        assertThat(member.getName()).isEqualTo("lavine");
        assertThat(member.getAge()).isEqualTo(26);
    }

    @Test
    void findMembersByAgeOverTest() {
        List<MemberDto> members = memberRepository.findMembersByAgeOver(30);

        assertThat(members.size()).isEqualTo(5);
    }
}
