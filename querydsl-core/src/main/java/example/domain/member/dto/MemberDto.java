package example.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberDto {
    private String name;
    private int age;

    public MemberDto() {

    }

    public MemberDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
