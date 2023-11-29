package com.test.rmsoft.rent.repository;

import com.test.rmsoft.member.entity.Member;
import com.test.rmsoft.rent.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    Optional<Rent> findByMemberAndRentState(Member member, Rent.RentState rentState);
}
