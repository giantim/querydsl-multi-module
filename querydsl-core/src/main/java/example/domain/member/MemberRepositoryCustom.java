package example.domain.member;

import example.domain.member.dto.MemberDto;

import java.util.List;

public interface MemberRepositoryCustom {
    Member findByName(String name);

    List<MemberDto> findMembersByAgeOver(int age);
}
