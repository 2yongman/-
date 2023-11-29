package com.test.rmsoft.member.service;

import com.test.rmsoft.member.entity.Member;

public interface MemberService {

    void signup(Member member);

    Member getMember(String email);

}
