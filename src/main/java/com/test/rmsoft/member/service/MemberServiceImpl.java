package com.test.rmsoft.member.service;

import com.test.rmsoft.exception.BusinessLogicException;
import com.test.rmsoft.exception.ExceptionCode;
import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(Member member) {
        this.memberExistCheck(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        memberRepository.save(member);

    }

    @Override
    public Member getMember(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void memberExistCheck(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXIST);
        }
    }



}
