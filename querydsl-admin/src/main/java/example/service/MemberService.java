package example.service;

import example.domain.member.MemberRepository;
import example.domain.member.dto.MemberDto;
import example.domain.team.Team;
import example.domain.team.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public MemberService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    public List<MemberDto> showMembersOverAge(int age) {
        return memberRepository.findMembersOverAge(age);
    }

    public void showMember() {
        Team team = teamRepository.findAll().get(0);
        team.getMembers().stream()
                .forEach(member -> System.out.println(member.getName()));
    }
}
