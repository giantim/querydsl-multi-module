package example.domain.member;

import example.domain.member.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    Member findByName(String name);

    List<MemberDto> findMembersOverAge(int age);

    List<MemberDto> findMembersOverAgeAndContainWord(int age, String word);
}
