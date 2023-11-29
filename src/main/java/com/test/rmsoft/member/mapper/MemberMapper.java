package com.test.rmsoft.member.mapper;

import com.test.rmsoft.member.dto.SignupDto;
import com.test.rmsoft.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member signupDtoToMember(SignupDto signupDto){
        return Member.builder()
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .build();
    }
}
