package example.service;

import example.domain.member.MemberRepository;
import example.domain.member.dto.MemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDto> showMembersOverAge(int age) {
        return memberRepository.findMembersByAgeOver(age);
    }
}
