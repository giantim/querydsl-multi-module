package example.service;

import example.domain.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Sql("/member.sql")
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    void showMembersOverAgeTest() {
        List<MemberDto> members = memberService.showMembersOverAge(26);

        members.forEach(member -> {
            System.out.println("----------");
            System.out.println("name: " + member.getName());
            System.out.println(String.format("age: %d", member.getAge()));
        });
    }

    @Test
    void showMemberTest() {
        memberService.showMember();
    }
}
