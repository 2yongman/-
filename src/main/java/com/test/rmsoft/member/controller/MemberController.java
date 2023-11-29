package com.test.rmsoft.member.controller;

import com.test.rmsoft.member.dto.SignupDto;
import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.member.mapper.MemberMapper;
import com.test.rmsoft.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberMapper memberMapper;
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDto signupDto){
        Member member = memberMapper.signupDtoToMember(signupDto);
        memberService.signup(member);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }
}
